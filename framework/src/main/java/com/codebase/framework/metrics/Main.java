package com.codebase.framework.metrics;

import com.codahale.metrics.*;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/3.
 */
public class Main {

    public static void main(String[] args) {
        healthCheckTest(20);
        timerTest(20);
        histogramsTest(20);
        meterTest(20);
        counterTest(20);
        gaugesTest(20);
        jmxTest();
    }

    public static void jmxTest() {
        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        final MetricRegistry registry = new MetricRegistry();
        final JmxReporter reporter = JmxReporter.forRegistry(registry).registerWith(mBeanServer).build();
        reporter.start();
    }

    public static void healthCheckTest(int runSeconds) {
        final HealthCheckRegistry registry = new HealthCheckRegistry();
        registry.register("database", new DatabaseHealthCheck("mysql"));

        int sec = 0;
        while (sec < runSeconds) {
            try {
                Thread.sleep(1000);
                sec++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Map.Entry<String, HealthCheck.Result> entry : registry.runHealthChecks().entrySet()) {
                if (entry.getValue().isHealthy()) {
                    System.out.println(entry.getKey() + ": OK");
                } else {
                    System.out.println(entry.getKey() + ": disable");
                }
            }
        }

    }

    static class DatabaseHealthCheck extends HealthCheck {

        private final String database;
        private final Random random = new Random();

        public DatabaseHealthCheck(String database) {
            this.database = database;
        }

        @Override
        protected Result check() throws Exception {
            int value = random.nextInt(10);
            System.out.println("value: " + value);
            if (value < 5) {
                return Result.healthy();
            }
            return Result.unhealthy("Can't ping database");
        }
    }

    public static void timerTest(int runSeconds) {
        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        final Timer timer = registry.timer(MetricRegistry.name(Main.class, "get-latency"));
        Timer.Context ctx;
        final Random random = new Random();
        int sec = 0;
        while (sec < runSeconds) {
            ctx = timer.time();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.stop();
        }
    }

    public static void histogramsTest(int runSeconds) {
        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        final Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        registry.register(MetricRegistry.name(Main.class, "request", "histogram"), histogram);

        final Random random = new Random();
        int sec = 0;
        while (sec < runSeconds) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            histogram.update(random.nextInt(100000));
        }
    }

    public static void meterTest(int runSeconds) {
        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        final Meter meterTps = registry.meter(MetricRegistry.name(Main.class, "request", "tps"));
        final Random random = new Random();
        int sec = 0;
        while (sec < runSeconds) {
            int count = Math.abs(random.nextInt(5));
            System.out.println("count: " + count);
            for (int i = 0; i < count; i++) {
                meterTps.mark();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void counterTest(int runSeconds) {

        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        final Counter pendingJobs = registry.counter(MetricRegistry.name(Queue.class, "pending-jobs", "size"));

        final Queue<String> q = new LinkedBlockingQueue<>();
        final Random random = new Random();
        int sec = 0;
        while (sec < runSeconds) {
            try {
                Thread.sleep(1000);
                sec++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (random.nextDouble() > 0.7) {
                pendingJobs.dec();
                String job = q.poll();
                System.out.println("take job : " + job);
            } else {
                String job = "Job-" + sec;
                pendingJobs.inc();
                q.offer(job);
                System.out.println("add job : " + job);
            }
            sec++;
        }
    }

    public static void gaugesTest(int runSeconds) {

        final Queue<String> queue = new LinkedBlockingQueue<>();

        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(Main.class, "list", "size"),
                new Gauge<Integer>() {
                    public Integer getValue() {
                        return queue.size();
                    }
                });

        int sec = 0;
        while (sec < runSeconds) {
            try {
                Thread.sleep(2000);
                sec++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queue.add("Job-" + sec);
        }
    }

}


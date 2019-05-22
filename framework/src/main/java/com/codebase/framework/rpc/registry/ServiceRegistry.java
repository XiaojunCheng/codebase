package com.codebase.framework.rpc.registry;

import com.codebase.common.config.ConfigClient;
import com.codebase.common.config.ConfigException;
import com.codebase.common.config.ZookeeperConfigClient;
import lombok.Data;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 初始化顺序：
 * construct
 * setRegistryAddress
 * setBeanName
 * setBeanFactory
 * setApplicationContext
 * doInit
 * afterPropertiesSet
 * init0
 * postProcessBeforeInitialization
 * postProcessAfterInitialization
 * doDestroy
 * destroy
 * destroy0
 *
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 * @see <url>https://pic3.zhimg.com/80/754a34e03cfaa40008de8e2b9c1b815c_hd.jpg</url>
 */
@Data
public class ServiceRegistry implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    private String registryAddress;

    private ConfigClient configClient;

    public ServiceRegistry() {
        System.out.println("construct");
    }

    public void setRegistryAddress(String registryAddress) {
        System.out.println("setRegistryAddress");
        this.registryAddress = registryAddress;
    }

    public void init0() {
        System.out.println("init0");
    }

    public void destroy0() {
        System.out.println("destroy0");
    }

    @PostConstruct
    public void doInit() {
        System.out.println("doInit");
        this.configClient = new ZookeeperConfigClient(registryAddress);
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("doDestroy");
        configClient.close();
    }

    /**
     * 注册服务地址
     *
     * @param serverAddress
     */
    public void register(String serverAddress) {
        if (serverAddress != null) {
            String path = RegisterConstants.ZK_DATA_PATH + "/" + serverAddress;
            try {
                configClient.register(path, serverAddress);
            } catch (ConfigException e) {
                LOGGER.error("register error, path: {}, data: {}", path, serverAddress, e);
            }
        }
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return bean;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}

package com.codebase.framework.rpc.registry;

import com.codebase.common.config.ConfigClient;
import com.codebase.common.config.ConfigException;
import com.codebase.common.config.ZookeeperConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class ServiceDiscovery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDiscovery.class);

    private volatile List<String> providers = new ArrayList<>();

    private final String registryAddress;
    private final ConfigClient configClient;

    public ServiceDiscovery(String registryAddress) throws ConfigException {
        this.registryAddress = registryAddress;
        this.configClient = new ZookeeperConfigClient(registryAddress);
        this.configClient.watchChildren(RegisterConstants.ZK_DATA_PATH, (state, path) -> {
            try {
                List<String> children = configClient.getChildren(RegisterConstants.ZK_DATA_PATH);
                if (!children.isEmpty()) {
                    providers = children;
                }
            } catch (ConfigException e) {
                LOGGER.error("fetch children error, path:{}, state: {}", path, state, e);
            }
        });
    }

    public String discover() {
        if (providers.isEmpty()) {
            return null;
        }
        int size = providers.size();
        if (size == 1) {
            LOGGER.debug("using only data: {}", providers.get(0));
            return providers.get(0);
        }

        int index = ThreadLocalRandom.current().nextInt(size);
        LOGGER.debug("using random data: {}", providers.get(index));
        return providers.get(index);
    }
}

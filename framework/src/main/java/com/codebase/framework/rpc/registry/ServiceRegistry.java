package com.codebase.framework.rpc.registry;

import com.codebase.common.config.ConfigClient;
import com.codebase.common.config.ConfigException;
import com.codebase.common.config.ZookeeperConfigClient;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
@Data
public class ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    private final String registryAddress;

    private final ConfigClient configClient;

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
        this.configClient = new ZookeeperConfigClient(registryAddress);
    }

    /**
     * 注册服务地址
     *
     * @param data
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

}

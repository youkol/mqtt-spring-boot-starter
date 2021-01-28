/**
 * Copyright (C) 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.youkol.support.mqtt.spring.autoconfigure;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * MqttAutoConfiguration
 *
 * @author jackiea
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ MqttClient.class, MqttConnectOptions.class })
@ConditionalOnMissingBean({ MqttClient.class, MqttConnectOptions.class })
@EnableConfigurationProperties(MqttProperties.class)
@ConditionalOnProperty(name = "youkol.mqtt.enabled", matchIfMissing = true)
@Import(MqttConnectOptionsConfiguration.class)
public class MqttAutoConfiguration {

    private final MqttProperties properties;

    public MqttAutoConfiguration(MqttProperties properties) {
        this.properties = properties;
    }

    private static final String DEFAULT_SERVER_URI = "tcp://127.0.0.1:1883";

    @Bean(destroyMethod = "disconnect")
    @ConditionalOnMissingBean(MqttClient.class)
    @SuppressWarnings("squid:S2095")
    MqttClient mqttClient(MqttConnectOptions options) throws MqttException {
        String broker = determineServerUri();
        String clientId = properties.getClientId();

        MemoryPersistence persistence = new MemoryPersistence();

        MqttClient client = new MqttClient(broker, clientId, persistence);
        client.connect(options);

        return client;
    }

    String determineServerUri() {
        if (properties.getServerUri() != null && properties.getServerUri().length > 0) {
            return properties.getServerUri()[0];
        }

        return DEFAULT_SERVER_URI;
    }

}

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

import java.nio.charset.StandardCharsets;

import com.youkol.support.mqtt.spring.autoconfigure.ConnectionProperties.WillProperties;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Configuration for MQTT {@link MqttConnectOptions}.
 *
 * @author jackiea
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(MqttConnectOptions.class)
class MqttConnectOptionsConfiguration {

    @Bean
    MqttConnectOptions mqttConnectOptions(MqttProperties properties, ObjectProvider<MqttConnectOptionsCustomizer> customizers) {
        MqttConnectOptions options = new MqttConnectOptions();

        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(properties::getServerUri).to(options::setServerURIs);
        map.from(properties::getUserName).to(options::setUserName);
        map.from(properties::getPassword).as(String::toCharArray).to(options::setPassword);
        map.from(properties::getKeepAliveInterval).to(options::setKeepAliveInterval);
        map.from(properties::getConnectionTimeout).to(options::setConnectionTimeout);
        map.from(properties::getMaxInflight).to(options::setMaxInflight);
        map.from(properties::isCleanSession).to(options::setCleanSession);
        map.from(properties::isAutomaticReconnect).to(options::setAutomaticReconnect);
        map.from(properties::getMaxReconnectDelay).to(options::setMaxReconnectDelay);
        map.from(properties::getExecutorServiceTimeout).to(options::setExecutorServiceTimeout);

        WillProperties will = properties.getWill();
        if (StringUtils.hasText(will.getTopic()) && StringUtils.hasText(will.getPayload())) {
            options.setWill(will.getTopic(), will.getPayload().getBytes(StandardCharsets.UTF_8),
                will.getQos(), will.getRetained());
        }

        // Customize the MqttConnectOptions
        customizers.orderedStream().forEach(t -> t.customize(options));

        return options;
    }


}

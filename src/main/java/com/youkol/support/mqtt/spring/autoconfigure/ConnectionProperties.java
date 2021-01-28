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

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * ConnectionProperties
 *
 * @author jackiea
 */
public class ConnectionProperties {

    /**
     * MQTT broker uri.
     *
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setServerURIs(String[])
     */
    private String[] serverUri = new String[] { "tcp://127.0.0.1:1883" };

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setUserName(String)
     */
    private String username;

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setPassword(char[])
     */
    private String password;

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setKeepAliveInterval(int)
     */
    private Integer keepAliveInterval = 60;

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setConnectionTimeout(int)
     */
    private Integer connectionTimeout = 30;

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setMaxInflight(int)
     */
    private Integer maxInflight = 10;
    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setCleanSession(boolean)
     */
    private boolean cleanSession = true;

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setAutomaticReconnect(boolean)
     */
    private boolean automaticReconnect = false;
    /**
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setMaxReconnectDelay(int)
     */
    private Integer maxReconnectDelay = 128000;

    /**
     * Client Operation Parameters How long to wait in seconds when terminating the
     * executor service.
     *
     * @see org.eclipse.paho.client.mqttv3.MqttConnectOptions#setExecutorServiceTimeout(int)
     */
    private Integer executorServiceTimeout = 1;

    @NestedConfigurationProperty
    private final WillProperties will = new WillProperties();

    public String[] getServerUri() {
        return serverUri;
    }

    public void setServerUri(String[] serverUri) {
        this.serverUri = serverUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public void setKeepAliveInterval(Integer keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getMaxInflight() {
        return maxInflight;
    }

    public void setMaxInflight(Integer maxInflight) {
        this.maxInflight = maxInflight;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    public void setAutomaticReconnect(boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }

    public Integer getMaxReconnectDelay() {
        return maxReconnectDelay;
    }

    public void setMaxReconnectDelay(Integer maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
    }

    public Integer getExecutorServiceTimeout() {
        return executorServiceTimeout;
    }

    public void setExecutorServiceTimeout(Integer executorServiceTimeout) {
        this.executorServiceTimeout = executorServiceTimeout;
    }

    public WillProperties getWill() {
        return will;
    }


    public static class WillProperties {

        private String topic;

        private String payload;

        private Integer qos = 0;

        private Boolean retained = false;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public Integer getQos() {
            return qos;
        }

        public void setQos(Integer qos) {
            this.qos = qos;
        }

        public Boolean getRetained() {
            return retained;
        }

        public void setRetained(Boolean retained) {
            this.retained = retained;
        }

    }

}

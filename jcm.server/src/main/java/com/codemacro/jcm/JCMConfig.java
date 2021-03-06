/*******************************************************************************
 *  Copyright Kevin Lynx (kevinlynx@gmail.com) 2015
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.codemacro.jcm;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jcm")
public class JCMConfig {

  @Autowired
  ServerProperties serverConfig;
  
  @Value("${zookeeper.host}")
  @NotNull
  public String zkHost;

  @Value("${zookeeper.root: /jcm}")
  @NotNull
  public String zkRoot;

  @Value("${zookeeper.timeout: 5000}")
  public int zkTimeout;

  @Value("${health.interval: 1000}")
  public int healthCheckInterval;

  public int getServerPort() {
    return serverConfig.getPort();
  }
  
  public String getServerAddr() throws UnknownHostException {
    InetAddress addr = serverConfig.getAddress();
    if (addr == null) {
      addr = InetAddress.getLocalHost();
    } 
    return addr.getHostAddress();
  }
}

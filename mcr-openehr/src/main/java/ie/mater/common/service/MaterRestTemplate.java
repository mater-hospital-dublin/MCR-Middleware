/*
 * Copyright 2016 Mater Care Record
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
 */
package ie.mater.common.service;

import javax.annotation.PostConstruct;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MaterRestTemplate extends RestTemplate {

    @Value("${network.proxy.set}")
    private Boolean proxySet;

    @Value("${network.proxy.host}")
    private String proxyHost;

    @Value("${network.proxy.port}")
    private Integer proxyPort;

    @Value("${network.proxy.user}")
    private String proxyUser;

    @Value("${network.proxy.password}")
    private String proxyPassword;

    @PostConstruct
    private void postConstruct() {
        if (proxySet) {
            setRequestFactory(requestFactory());
        }
        else {
            setRequestFactory(new SimpleClientHttpRequestFactory());
        }
    }

    private ClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        InetSocketAddress address = new InetSocketAddress(proxyHost, proxyPort);

        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        factory.setProxy(proxy);

        Authenticator.setDefault(
            new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(proxyUser, proxyPassword.toCharArray());
                }
            }
        );

        return factory;
    }
}

package com.example.vpbank.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Client;
import java.net.Proxy;
import java.net.InetSocketAddress;
@Configuration
public class FTVanFeignConfig {
    @Value("${ftvan-username}")
    private String username;
    @Value("${ftvan-password}")
    private String password;

//    @Value("${ftvan-password}")
//    private String type;
//    @Value("${proxy-host}")
//    private String proxyHost;
//    @Value(("${proxy-port}"))
//    private String proxyPort;


    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }
//    @Bean
//    public Client feignClientHTTP() {
//        return new Client.Proxied(null, null,
//                new Proxy(Proxy.Type.HTTP,
//                        new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort))));
//    }
//    @Bean
//    public Client feignClientSOCKS() {
//        return new Client.Proxied(null, null,
//                new Proxy(Proxy.Type.SOCKS,
//                        new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort))));
//    }
//    @Bean
//    public Client feignClientDIRECT() {
//        return new Client.Proxied(null, null,
//                new Proxy(Proxy.Type.DIRECT,
//                        new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort))));
//    }
//    @Bean
//    public CloseableHttpClient feignClient1() {
//        return HttpClientBuilder.create().setProxy(
//                new HttpHost("proxyHost", Integer.parseInt("proxyPort"))).build();
//    }
//    @Bean
//    public OkHttpClient okHttpClient() {
//        return new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.SOCKS,
//                        new InetSocketAddress("proxyHost", Integer.parseInt("proxyPort"))))
//                .build();
//    }
    @Bean
    Logger.Level ftVanFeignLoggerLevel(){ return Logger.Level.FULL;}
}

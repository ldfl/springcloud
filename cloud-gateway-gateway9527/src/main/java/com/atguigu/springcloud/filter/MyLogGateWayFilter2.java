package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @auther zzyy
 * @create 2020-02-21 16:40
 */
@Configuration
@Slf4j
public class MyLogGateWayFilter2 {
    @Bean
    public GlobalFilter globalFilter() {
        GlobalFilter globalFilter = new GlobalFilter(){
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("***********come in MyLogGateWayFilter:  "+new Date());

                String uname = exchange.getRequest().getQueryParams().getFirst("uname");

                if(uname == null)
                {
                    log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
                    exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                    return exchange.getResponse().setComplete();
                }

                return chain.filter(exchange);
            }
        };
        return globalFilter;
    }
}

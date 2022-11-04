package com.bee.config;

import com.alibaba.fastjson.JSONObject;
import com.bee.common.Response;
import com.bee.utils.JWTUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 用户鉴权的全局过滤器
 */
@ConfigurationProperties("org.my.jwt")
@Component
@Slf4j
@Data
public class UserAuthGlobalFilter implements GlobalFilter, Ordered {
    private String[] skipAuthUris;

    private boolean shouldSkip(String uri) {
        for (String skipUri : skipAuthUris) {
            if (uri.startsWith(skipUri)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //user/login 放行
        String path = exchange.getRequest().getURI().getPath();
        if (skipAuthUris != null && shouldSkip(path)) {
            return chain.filter(exchange);
        }

        //从请求头中获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtil.isNullOrEmpty(token)) {
            //设置响应
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            Response res = Response.builder().code(200).msg("token 参数缺失").build();
            //对象转成字符串
            byte[] bytes = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(wrap));
        }
        boolean verify = JWTUtils.verify(token);

        if (!verify) {
            //设置响应
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            Response res = Response.builder().code(200).msg("token 失效").build();
            //对象转成字符串
            byte[] bytes = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(wrap));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

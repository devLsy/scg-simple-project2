package com.test.lsy.apigateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
// 지역 필터 테스트 용도
public class L1Filter extends AbstractGatewayFilterFactory<L1Filter.Config> {

    public L1Filter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // pre에 대한 내용(마이크로 서비스 거치기 전)
        return (exchange, chain) -> {
            if (config.isPre()) {
                log.info("pre local filter 1");
            }
            return chain.filter(exchange)
                // post에 대한 내용(마이크로 서비스 거친 후 다시 게이트웨이 필터에서 수행할 내용)
                .then(Mono.fromRunnable(() -> {
                    if (config.isPost()) {
                        log.info("post local filter 1");
                    }
                }));
        };
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    // 지역필터에서 받을 변수를 선언
    public static class Config {
        private boolean pre;
        private boolean post;
    }
}

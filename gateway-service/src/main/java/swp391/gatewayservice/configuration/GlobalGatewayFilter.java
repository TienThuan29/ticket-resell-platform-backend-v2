package swp391.gatewayservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import swp391.gatewayservice.service.JwtService;

@Slf4j
@Component
public class GlobalGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global filter.....");
        ServerHttpRequest request = exchange.getRequest();
        if(routerValidator.isSecured.test(request)) {
            if(authMissing(request)) {
                log.info("Auth missing");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            final String token =  request.getHeaders().getOrEmpty("Authorization")
                    .get(0).substring(7); // "Bearer ".length()
            if (token.isEmpty() || jwtService.isTokenExpired(token)) {
                log.info("Invalid token");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}

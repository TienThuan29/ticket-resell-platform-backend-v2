package swp391.gatewayservice.configuration;

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

@Component
public class GlobalGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    private RouterValidator routerValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Global filter.....");
        ServerHttpRequest request = exchange.getRequest();
        if(routerValidator.isSecured.test(request)) {
//            if(authMissing(request)) {
//                System.out.println("Auth missing");
//                return onError(exchange, HttpStatus.UNAUTHORIZED);
//            }
//
//            final String token =  request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
//            System.out.println("Token: "+token);
//            if (token.isEmpty()) {
//                System.out.println("Expired token");
//                return  onError(exchange, HttpStatus.UNAUTHORIZED);
//            }
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

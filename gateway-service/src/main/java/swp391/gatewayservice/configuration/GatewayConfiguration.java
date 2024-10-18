package swp391.gatewayservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfiguration {

    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "user-service",
                        r -> r.path(
                                "/api/users/**",
                                "/api/auth/**"
                        )
                        .uri("http://localhost:9002")
                )
                .route(
                        "ticket-service",
                        r -> r.path(
                                "/api/tickets/**",
                                "/api/tickets/generic/**",
                                "/api/events/**",
                                "/api/categories/**",
                                "/api/hashtags/**"
                        )
                       .uri("http://localhost:9003")
                )
                .route(
                        "admin-service",
                        r -> r.path(
                                "/api/admin/**",
                                "/api/policy/**"
                        )
                        .uri("http://localhost:9004")
                )
                .route(
                        "staff-service",
                        r -> r.path(
                                "/api/staffs/**"
                        )
                        .uri("http://localhost:9005")
                )
                .route(
                        "report-service",
                        r -> r.path(
                                "/api/reports/**"
                        )
                        .uri("http://localhost:9006")
                )
                .route(
                        "email-service",
                        r -> r.path(
                                "/api/email/**"
                        )
                        .uri("http://localhost:9007")
                )
                .route(
                        "payments-service",
                        r -> r.path(
                                "/api/payments/**",
                                "/api/transactions/**"
                        )
                        .uri("http://localhost:9008")
                )
                .route(
                        "chat-service",
                        r -> r.path(
                                "/api/chat/**"
                        )
                        .uri("http://localhost:9009")
                )
                .build();
    }

}

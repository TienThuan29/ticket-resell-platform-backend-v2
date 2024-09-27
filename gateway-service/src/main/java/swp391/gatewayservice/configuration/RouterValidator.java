package swp391.gatewayservice.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndPoints = List.of(
            "/api/users/**",
            "/api/tickets/**",
            "/api/tickets/generic/**",
            "/api/events/**",
            "/api/categories/**",
            "/api/admin/**",
            "/api/policy/**",
            "/api/staffs/**",
            "/api/reports/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openEndPoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
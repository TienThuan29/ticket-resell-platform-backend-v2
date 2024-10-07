package swp391.gatewayservice.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

//    public static final List<String> openEndPoints = List.of(
//            "/api/users/**",
//            "/api/tickets/**",
//            "/api/tickets/generic/**",
//            "/api/events/**",
//            "/api/categories/**",
//            "/api/admin/**",
//            "/api/policy/**",
//            "/api/staffs/**",
//            "/api/reports/**",
//            "/api/hashtags/**",
//            "/api/email/**",
//            "/api/payments/**",
//            "/api/auth/**"
//    );

    public static final List<String> openEndPoints = List.of(
            "/api/users/authenticate",
            "/api/users/register",
            "/api/users/refresh-token",
            "/api/users/register/verify-email",
            "/api/tickets/generic/get-by-event",
            "/api/tickets/generic/get-total-ticket",
            "/api/policy/get/selling",
            "/api/events/get-happening-events",
            "/api/events/get-happening-events",
            "/api/categories/get-using-cate",
            "/api/email/send-otp",
            "/api/email/send-reset-otp",
//            "/api/admin/register-staff",  // Open it if you want to create admin account
            "/api/system/authentication",
            "/api/system/refresh-token",
            "/api/tickets/generic/get-by-filter",
            "/api/users/reset-password",
            "/api/users/reset-password/verify-reset-otp",
            "/api/users/reset-password/new-pass"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openEndPoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
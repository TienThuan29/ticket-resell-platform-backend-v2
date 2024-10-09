package swp391.gatewayservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("gatewayServiceConstantConfiguration")
@PropertySource("classpath:security.properties")
public class ConstantConfiguration {

    public final String AUTHENTICATION_HEADER;
    public final String AUTHENTICATION_HEADER_BEARER;
    public final String JWT_SECRET_KEY;
    public final Long JWT_TOKEN_EXPIRATION;
    public final Long REFRESH_TOKEN_EXPIRATION;
    public final Boolean JWT_REVOKED_DISABLE;
    public final Boolean JWT_REVOKED_ENABLE;
    public final Boolean JWT_EXPIRED_DISABLE;
    public final Boolean JWT_EXPIRED_ENABLE;


    public ConstantConfiguration(
            @Value("${auth.header}") String authHeader,
            @Value("${auth.header.bearer}") String authHeaderBearer,
            @Value("${jwt.secret-key}") String jwtSecretKey,
            @Value("${jwt.expiration}") Long jwtTokenExpiration,
            @Value("${jwt.refresh-token.expiration}") Long refreshTokenExpiration,
            @Value("${jwt.expired.disable}") Boolean jwt_expired_disable,
            @Value("${jwt.expired.enable}") Boolean jwt_expired_enable,
            @Value("${jwt.revoked.disable}") Boolean jwt_revoked_disable,
            @Value("${jwt.revoked.enable}") Boolean jwt_revoked_enable
    ) {
        this.AUTHENTICATION_HEADER = authHeader;
        this.AUTHENTICATION_HEADER_BEARER = authHeaderBearer;
        this.JWT_SECRET_KEY = jwtSecretKey;
        this.JWT_TOKEN_EXPIRATION = jwtTokenExpiration;
        this.REFRESH_TOKEN_EXPIRATION = refreshTokenExpiration;
        this.JWT_REVOKED_DISABLE = jwt_revoked_disable;
        this.JWT_REVOKED_ENABLE = jwt_revoked_enable;
        this.JWT_EXPIRED_DISABLE = jwt_expired_disable;
        this.JWT_EXPIRED_ENABLE = jwt_expired_enable;
    }

}

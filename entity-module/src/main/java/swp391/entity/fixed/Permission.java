package swp391.entity.fixed;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    STAFF_READ("staff:read"),
    STAFF_UPDATE("staff:update"),
    STAFF_CREATE("staff:create"),
    STAFF_DELETE("staff:delete")

    ;

    private final String permission;

}

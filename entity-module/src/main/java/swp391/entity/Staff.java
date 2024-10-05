package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import swp391.entity.fixed.Role;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "staffs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Staff implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , columnDefinition = "nvarchar(100)",unique = true,nullable = false)
    private String username;

    @Column(name = "password" , length = 512 ,nullable = false)
    private String password;

    @Column(name = "firstname" , columnDefinition = "nvarchar(128)",nullable = false)
    private String firstname;

    @Column(name = "lastname" , columnDefinition = "nvarchar(128)",nullable = false)
    private String lastname;

    @Column(name = "email" , length = 128 ,nullable = false)
    private String email;

    @Column(name = "phone" , length = 15)
    private String phone;

    @Lob
    @Column(name = "avatar" ,columnDefinition = "varbinary(MAX)")
    private byte[] avatar;

    @Column(name = "is_enable", columnDefinition = "bit default 1")
    private Boolean isEnable;

    @Column(name = "role_code")
    @Enumerated(EnumType.STRING)
    private Role roleCode;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "staff_code", nullable = false)
    private String staffCode;

    @OneToMany(mappedBy = "verifyStaff", fetch = FetchType.LAZY)
    private List<Ticket> verifiedTickets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleCode.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isEnable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isEnable;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}

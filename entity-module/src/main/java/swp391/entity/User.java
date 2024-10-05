package swp391.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import swp391.entity.fixed.Role;
import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , columnDefinition = "nvarchar(100)",unique = true, nullable = false)
    private String username;

    @Column(name = "password" , length = 512 ,nullable = false)
    private String password;

    @Column(name = "firstname" , columnDefinition = "nvarchar(128)",nullable = false)
    private String firstname;

    @Column(name = "lastname" , columnDefinition = "nvarchar(128)",nullable = false)
    private String lastname;

    @Column(name = "email" , length = 128 ,nullable = false)
    private String email;

    @Column(name = "phone" , length = 12)
    private String phone;

    @Lob
    @Column(name = "avatar" ,columnDefinition = "varbinary(MAX)")
    private byte[] avatar;

    @Column(name = "is_enable",columnDefinition = "bit default 1")
    private Boolean isEnable;

    @Column(name = "role_code")
    @Enumerated(EnumType.STRING)
    private Role roleCode;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "customer_code", nullable = false)
    private String customerCode;

    @Column(name = "is_seller", columnDefinition = "bit default 0")
    private Boolean isSeller;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Collection<GenericTicket> genericTickets;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Transaction> historyTransactions;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private Collection<Rating> ratingList;

    @OneToMany(mappedBy = "accuser", fetch = FetchType.LAZY)
    private Collection<ReportFraud> reportFrauds;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private Collection<OrderTicket> orderTickets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleCode.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

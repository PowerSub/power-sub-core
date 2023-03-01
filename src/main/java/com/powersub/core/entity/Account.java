package com.powersub.core.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "accounts")
public class Account implements UserDetails {

  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @ManyToMany
  @JoinTable(
          name = "subscribes",
          joinColumns = @JoinColumn(name = "account_id"),
          inverseJoinColumns = @JoinColumn(name = "channel_id"))
  private Set<Channel> channelsSubscriber = new HashSet<>(); //todo назвать нормально

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(email, account.email) && Objects.equals(password, account.password) && Objects.equals(createdAt, account.createdAt) && Objects.equals(channelsSubscriber, account.channelsSubscriber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, createdAt, channelsSubscriber);
  }
}

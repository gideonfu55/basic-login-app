package com.project.login.entity;

import com.project.login.entity.role.Role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userlogin")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Username cannot be blank")
  @NonNull
  @Column(name = "username", nullable = false)
  private String username;
  
  @NotBlank(message = "Password cannot be blank")
  @NonNull
  @Column(name = "password", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Override
  public String toString() {
    return "{" +
      " username='" + getUsername() + "'" +
      ", password='" + getPassword() + "'" +
      "}";
  }

}

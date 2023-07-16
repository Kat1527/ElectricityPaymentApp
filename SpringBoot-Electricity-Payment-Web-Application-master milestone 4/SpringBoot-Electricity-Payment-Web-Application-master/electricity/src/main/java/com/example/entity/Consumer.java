package com.example.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.payloads.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consumer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Email(message = "Invalid email format")
  @Column(unique = true)
  @UniqueEmail(message = "Email already exists")
  private String email;
  private String password;
  private String name;
  
  @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  
  private List<Property> properties;
   
  private double amount;
  
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  // Other attributes and relationships

  

@Override
public String toString() {
	return "Consumer [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", amount="
			+ amount + "]";
}

  // Constructors, getters, and setters






}

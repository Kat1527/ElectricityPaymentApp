package com.example.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.payloads.UniqueEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String name;
	  private String password;
	  
	  @Email(message = "Invalid email format")
	    @Column(unique = true)
	    @UniqueEmail(message = "Email already exists")
	  private String email;
	  
	  @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	  
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	        return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));

	  
	  	    }

}

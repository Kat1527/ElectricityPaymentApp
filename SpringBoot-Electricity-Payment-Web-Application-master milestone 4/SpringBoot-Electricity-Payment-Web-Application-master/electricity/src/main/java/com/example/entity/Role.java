package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "role_id")
	    private Integer roleId;

	    private String authority;
	
	
//	  @ManyToMany(mappedBy = "roles")
//	    private Set<User> users = new HashSet<>();

	 

}

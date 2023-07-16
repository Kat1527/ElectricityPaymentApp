package com.example.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.entity.Role;
import com.example.repository.UserRepository;

@Configuration
public class AppConfig {
	
	@Autowired 
	UserRepository userRepository;
	
	
	 @Bean
		public UserDetailsService userDetailsService()
		{
			UserDetails userDetails1 = User.builder().
										username("admin@gmail.com").
									password(passwordEncoder().encode("admin")).roles("ADMIN").build();
			
			UserDetails userDetails2 = User.builder().
					username("official@gmail.com").
				password(passwordEncoder().encode("official")).roles("OFFICIAL").build();
			
			UserDetails userDetails3 = User.builder().
					username("john@gmail.com").
				password(passwordEncoder().encode("john")).roles("CONSUMER").build();
			
//			List<UserDetails> allUserDetails=new ArrayList<>();
//			
//			for(com.example.entity.User u: userRepository.findAll())
//			{
//				User.builder().
//				username(u.getUsername()).
//				password(passwordEncoder().encode(u.getPassword())).roles(conversion(u.getRoles())).build();
//				
//			}
			
			 return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
			
			//  return new UserInfoUserDetailsService();
		}

	
	
	
	String[] conversion(Set<Role> setRoles)
	{
		String list[] = new String[setRoles.size()];
		int i=0;
		for(Role r:  setRoles)
		{
			list[i] = r.getAuthority();
			i++;
		}
		
		 
		
		return list;
	}
	
//	@Bean
//    public AuthenticationManager authManager(UserDetailsService detailsService) {
//        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
//        daoProvider.setUserDetailsService(detailsService);
//        daoProvider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(daoProvider);
//    }
	
//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;
//
//    
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
	
	 @Bean
		public PasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
		{
			return builder.getAuthenticationManager();
		}
 

}

package com.AB.bookServer.JwtConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.AB.bookServer.JwtFilter.JwtFilter;
import com.AB.bookServer.services.UserServiceForJWT;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtFilter myFilter;

	@Autowired
	private UserServiceForJWT customUser;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUser);
	}

	@Bean
	public PasswordEncoder passwordencoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
// "/user/getUser"
	
	// 	httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user/login","/user/addNewUser","/user/getCookies","/books").permitAll().anyRequest()
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
<<<<<<< Updated upstream

		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user/login","/user/addNewUser","/books","/books/page","/books/search").permitAll().anyRequest()
=======
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user/login","/user/addNewUser","/user/getUser","/books","/books/page","/books/search").permitAll().anyRequest()
>>>>>>> Stashed changes
				.authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

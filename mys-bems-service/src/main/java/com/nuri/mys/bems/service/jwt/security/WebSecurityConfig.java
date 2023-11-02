package com.nuri.mys.bems.service.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
				.antMatchers("**/swagger-ui/**").permitAll()
				.antMatchers("**/error/**").permitAll()
				;
//				.antMatchers("/pms/management/**").hasRole("SYSTEM")
//				.antMatchers("/pms/control/**").hasRole("SYSTEM");
		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}
}
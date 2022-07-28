package com.cart.shoppingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cart.shoppingapp.service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception
	{
		//Setting Service to find User in the database and Setting Password Encoder.
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception
	{
		http.csrf().disable();
		//Requires login with role ROLE_EMPLOYEE or ROLE_Manager. If not, it will redirect to admin/login.
		http.authorizeRequests().antMatchers("/admin/orderList","/admin/order","/admin/acccountinfo").access("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')");
		
		//PAGES only for MANAGER
		http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");
		
		//When USER login role ABC but access page requires XYZ role, an AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//Configuration for LOGIN_FORM
		/*
		 *  .loginProcessingUrl("/j_spring_security_check") // Submit URL
		 *   // Configuration for the Logout page.
            // (After logout, go to home page)
            .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");

		 */
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/admin/login").defaultSuccessUrl("/admin/accountInfo").failureUrl("/admin/login?error=true").usernameParameter("userName") .passwordParameter("password") .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");


	}

}
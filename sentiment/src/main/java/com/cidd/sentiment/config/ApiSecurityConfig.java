package com.cidd.sentiment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.cidd.sentiment.security.ApiAuthenticationEntryPoint;
import com.cidd.sentiment.security.ApiAuthenticationFailureHandler;
import com.cidd.sentiment.security.ApiAuthenticationSuccessHandler;
import com.cidd.sentiment.security.ApiLogoutSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ApiAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private ApiAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private ApiAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private ApiLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private TokenBasedRememberMeServices rememberMeServices;

	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @Bean
    public ObjectMapper mapper() {
        return new MappingJackson2HttpMessageConverter().getObjectMapper();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true)
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
     		.antMatcher("/api/**")
     		.csrf().disable()
     		.authorizeRequests().antMatchers("/api/authenticate").permitAll()
                .and()
            .authorizeRequests().anyRequest().authenticated()
            	.and()
            .formLogin()
                .permitAll()
                .loginProcessingUrl("/api/authenticate")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
            .logout()
                .logoutUrl("/api/logout")
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices)
                .key("remember-me-key");
            //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
//        	.and()
//            	.apply(new SpringSocialConfigurer());
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        
    }
}
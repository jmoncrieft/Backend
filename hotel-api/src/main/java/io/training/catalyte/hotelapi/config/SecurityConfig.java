package io.training.catalyte.hotelapi.config;

import static io.training.catalyte.hotelapi.constants.StringConstants.MANAGER_ROLE_TYPE;

import io.training.catalyte.hotelapi.security.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {
      // -- swagger ui
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**"
  };

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins("http://localhost:3000");
      }
    };
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(new AuthFilter(), AuthFilter.class)
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.GET).permitAll()
        .antMatchers(HttpMethod.POST, "/rooms").hasAuthority(MANAGER_ROLE_TYPE)
        .antMatchers(HttpMethod.PUT, "/rooms").hasAuthority(MANAGER_ROLE_TYPE)
        .antMatchers(HttpMethod.DELETE, "/rooms").hasAuthority(MANAGER_ROLE_TYPE)
        .and()
        .sessionManagement().disable()
        .csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(HttpMethod.OPTIONS);
    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
        "/configuration/security", "/swagger-ui.html", "/webjars/**", "/login");
  }

}

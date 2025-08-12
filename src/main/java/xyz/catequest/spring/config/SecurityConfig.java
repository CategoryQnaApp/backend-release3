package xyz.catequest.spring.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import xyz.catequest.spring.domain.user.enums.UserRole;
import xyz.catequest.spring.global.exception.AccessDeniedHandlerImpl;
import xyz.catequest.spring.global.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  @SuppressWarnings("squid:S4502")
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // This is a stateless REST API - CSRF protection is not needed
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(
            cors ->
                cors.configurationSource(
                    request -> {
                      CorsConfiguration config = new CorsConfiguration();
                      config.addAllowedOriginPattern("*"); // 테스트용 실제 배포시 실제 도메인만 허용하도록 변경
                      config.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                      config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                      config.setAllowCredentials(true);
                      return config;
                    }))
        .exceptionHandling(
            configurer -> configurer.accessDeniedHandler(new AccessDeniedHandlerImpl()))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin(AbstractHttpConfigurer::disable)
        .anonymous(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .rememberMe(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests( // todo : 실제 배포시 권한 내용 추가하기
            auth ->
                auth.requestMatchers("/api/v*/auth/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.PATCH, "/api/v*/users/*/role")
                    .hasAuthority(UserRole.ADMIN.getRoleName())
                    .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**")
                    .permitAll() // swagger
                    .requestMatchers("/actuator", "/actuator/**", "/_cluster/health")
                    .permitAll() // Spring Actuator
                    .anyRequest()
                    .authenticated())
        .build();
  }
}

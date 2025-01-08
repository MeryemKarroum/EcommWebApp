package meryem.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        // Configure HttpSecurity
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless API
                .cors(cors -> cors.configurationSource(getCorsConfigurationSource())) // Enable CORS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/all").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/products/all").hasAnyAuthority("ADMIN", "CASHIER", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyAuthority("ADMIN", "CASHIER", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/categories/all").hasAnyAuthority("ADMIN", "CASHIER", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").hasAnyAuthority("ADMIN", "CASHIER", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/products/save").hasAuthority("CREATE")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/delete/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/update").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class) // Add JWTAuthenticationFilter
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWTAuthorizationFilter

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.applyPermitDefaultValues();
        configuration.addAllowedOrigin("http://localhost:4200"); // Allow specific origin
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setExposedHeaders(Arrays.asList("Authorization")); // Expose Authorization header
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

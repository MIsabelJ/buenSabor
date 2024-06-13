package com.entidades.buenSabor.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${AUTH0_AUDIENCE}")
    private String audience;

    @Value("${AUTH0_ISSUER_URI}")
    private String issuer;

    @Value("${CORS_ALLOWED_ORIGINS}")
    private String corsAllowedOrigins;

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors(withDefaults()) //por defecto spring va a buscar un bean con el nombre "corsConfigurationSource".
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                //TODOS LOS ENDPOINTS CON SU NIVEL DE ACCESO
                                .requestMatchers("/**").hasAuthority("ADMIN")
                                //.requestMatchers("/{idEmpresa}/sucursales").hasAuthority("ADMIN")
                                /*.requestMatchers(HttpMethod.POST, "/empresa").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/**").hasAnyAuthority("ADMIN", "ADMIN_NEGOCIO")
                                .requestMatchers(HttpMethod.POST, "/sucursal/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/sucursal").hasAnyAuthority("ADMIN", "ADMIN_NEGOCIO")
                                .requestMatchers(HttpMethod.DELETE, "/sucursal").hasAnyAuthority("ADMIN")
                                */
                                .requestMatchers("/**").authenticated()
                                .anyRequest().hasAuthority("ADMIN")
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt ->
                                        jwt
                                                .decoder(jwtDecoder())
                                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Configuración de los orígenes permitidos (allowed origins)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));

        // Permitir el envío de credenciales (cookies, encabezados de autenticación)
        configuration.setAllowCredentials(true);

        // Encabezados permitidos en las peticiones
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // Encabezados expuestos en la respuesta
        configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));

        // Tiempo máximo en segundos que el navegador puede cachear la configuración CORS
        configuration.setMaxAge(3600L);

        // Configurar la fuente de configuración basada en URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("https://buensaborapi/roles");
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug);
    }

}


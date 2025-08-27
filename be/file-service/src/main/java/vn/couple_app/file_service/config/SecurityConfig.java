package vn.couple_app.file_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    final JwtDecoderCustom jwtDecoderCustom;

    final String[] PUBLIC_URLS = {
            "/images/*"
    };

    public SecurityConfig(JwtDecoderCustom jwtDecoderCustom) {
        this.jwtDecoderCustom = jwtDecoderCustom;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .authorizeHttpRequests(
                request -> request
                    .requestMatchers(HttpMethod.GET, PUBLIC_URLS)
                    .permitAll()
                    .anyRequest()
                    .authenticated());

        httpSecurity.oauth2ResourceServer(
            oauth2 -> oauth2
                .jwt(jwtConfigurer ->
                    jwtConfigurer.decoder(jwtDecoderCustom))
                .authenticationEntryPoint(new JwtAuthEntryPoint())
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}

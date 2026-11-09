package br.com.tatuwallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() throws MalformedURLException {
        // 1. Coloque a URL de JWK-Set-URI do seu provedor de autenticação
        JWKSource<SecurityContext> jwkSource = new RemoteJWKSet<>(
                new URL("https://sua-url-de-auth.com/.well-known/jwks.json")
        );

        // 2. Configure o seletor do Nimbus (este sim suporta EdDSA nativamente)
        JWSKeySelector<SecurityContext> keySelector =
                new JWSVerificationKeySelector<>(JWSAlgorithm.EdDSA, jwkSource);

        // 3. Monte o processador de tokens do Nimbus
        DefaultJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWSKeySelector(keySelector);

        // 4. Entregue o processador já pronto para o Spring Security
        return new NimbusJwtDecoder(jwtProcessor);
    }
}
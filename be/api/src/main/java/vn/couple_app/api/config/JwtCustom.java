package vn.couple_app.api.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import vn.couple_app.api.entity.User;
import vn.couple_app.api.exception.AppException;
import vn.couple_app.api.exception.ErrorCode;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtCustom implements JwtDecoder {
    @Value("${jwt.signer-key}")
    private String signerKey;
    @Value("${jwt.valid-duration}")
    private long validDuration;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    public String encode(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
            .subject(user.getId())
            .issuer("couple-app.api")
            .issueTime(new Date())
            .expirationTime(
                    new Date(Instant.now()
                            .plus(validDuration, ChronoUnit.SECONDS)
                            .toEpochMilli()))
            .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            verifyToken(token);
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        } catch (AppException e) {
            throw new JwtException("Token invalid");
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
        }

        return nimbusJwtDecoder.decode(token);
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        //if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
        //    throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }
}

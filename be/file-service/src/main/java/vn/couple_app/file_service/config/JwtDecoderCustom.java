package vn.couple_app.file_service.config;

import com.nimbusds.jwt.SignedJWT;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import vn.couple_app.file_service.dto.request.IntrospectRequest;
import vn.couple_app.file_service.dto.response.IntrospectResponse;
import vn.couple_app.file_service.dto.response.ResponseApi;
import vn.couple_app.file_service.repository.httpclient.IdentityClient;

import java.text.ParseException;

@RequiredArgsConstructor
@Component
public class JwtDecoderCustom implements JwtDecoder {
    final IdentityClient identityClient;
    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            ResponseApi<IntrospectResponse> response = identityClient
                .introspect(IntrospectRequest
                    .builder()
                    .token(token)
                    .build());

            if (!response.getData().isValid())
                throw new JwtException("Invalid token");

            SignedJWT signedJWT = SignedJWT.parse(token);

            return new Jwt(token,
                signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                signedJWT.getHeader().toJSONObject(),
                signedJWT.getJWTClaimsSet().getClaims()
            );
        } catch (ParseException | FeignException e) {
            throw new JwtException("Invalid token");
        }
    }
}

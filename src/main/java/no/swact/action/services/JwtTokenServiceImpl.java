package no.swact.action.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.swact.action.models.Token;
import no.swact.action.models.User;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Key key;

    @Override
    public Token convert(final User user) {
        JsonWebEncryption jwe = buildEncryption();
        try {
            jwe.setPayload(objectMapper.writeValueAsString(user));
            return new Token(jwe.getCompactSerialization());
        } catch (JsonProcessingException | JoseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User convert(final String token) {
        JsonWebEncryption jsonWebEncryption = buildEncryption();
        try {
            jsonWebEncryption.setCompactSerialization(token);
            String payload = jsonWebEncryption.getPayload();
            return objectMapper.readValue(payload, User.class);
        } catch (JoseException | IOException e) {
            throw new RuntimeException("Could not deserialize token: " + token, e);
        }
    }

    private JsonWebEncryption buildEncryption() {
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        return jwe;
    }
}

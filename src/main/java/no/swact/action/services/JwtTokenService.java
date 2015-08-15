package no.swact.action.services;

import no.swact.action.models.User;
import no.swact.action.models.auth.Token;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    Token convert(User user);

    User convert(String token);
}

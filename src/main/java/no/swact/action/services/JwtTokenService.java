package no.swact.action.services;

import no.swact.action.models.Token;
import no.swact.action.models.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    Token convert(User user);

    User convert(String token);
}

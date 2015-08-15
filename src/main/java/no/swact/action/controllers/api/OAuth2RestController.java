package no.swact.action.controllers.api;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import no.swact.action.models.auth.Token;
import no.swact.action.models.User;
import no.swact.action.services.JwtTokenService;
import no.swact.action.services.UserService;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/authenticate")
public class OAuth2RestController {

    private static final Logger LOG = LoggerFactory.getLogger(OAuth2RestController.class);

    @Autowired
    private GoogleCredential googleCredential;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public Token exchange(@RequestBody String token) throws IOException, JoseException {
        LOG.info(token);
        googleCredential.setAccessToken(token);
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), googleCredential).setApplicationName(
                "ACTion").build();
        Userinfoplus userinfo = oauth2.userinfo().get().execute();
        User user = new User(userinfo, token);
        User saved = userService.save(user);

        return jwtTokenService.convert(saved);
    }
}

package no.swact.action.controllers.api;

import no.swact.action.models.User;
import no.swact.action.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/me")
    private User me() {
        return (User) SecurityContextHolder.getContext().getAuthentication();
    }
}

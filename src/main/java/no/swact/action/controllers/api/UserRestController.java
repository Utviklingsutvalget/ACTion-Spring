package no.swact.action.controllers.api;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.services.RoleService;
import no.swact.action.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/me")
    private User me() {
        User authentication = (User) SecurityContextHolder.getContext().getAuthentication();
        return userService.findById(authentication.getId());
    }

    @RequestMapping(value = "/me/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return userService.findById(user.getId()).getRoles();
    }
}

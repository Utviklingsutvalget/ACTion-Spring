package no.swact.action.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.models.exceptions.ResourceNotFoundException;
import no.swact.action.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public User me() throws JsonProcessingException {
        User user = getAuthentication();
        return userService.findById(user.getId());
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        User user = getAuthentication();
        return userService.findById(user.getId()).getRoles();
    }

    private User getAuthentication() {
        return (User) SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = "/search/{prefix}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String prefix) {
        final String email = prefix + User.EMAIL_SUFFIX;
        User byEmail = userService.findByEmail(email);
        if(byEmail == null) {
            throw new ResourceNotFoundException("Ingen bruker med epost " + email + " funnet!");
        }
        return byEmail;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST)
    public Map<String, String> addRoleToUser(@PathVariable String id, @RequestBody Role role) {
        User fromDb = userService.findById(id);

        List<Role> roles = fromDb.getRoles();
        if (roles.contains(role)) {
            return Collections.singletonMap("status", "role already present");
        } else {
            roles.add(role);
            userService.save(fromDb);
            return Collections.singletonMap("status", "ok");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/roles/{role}", method = RequestMethod.DELETE)
    public Map<String, String> removeRoleFromUser(@PathVariable String id, @PathVariable String role) {
        User fromDb = userService.findById(id);
        List<Role> roles = fromDb.getRoles();
        int rolesBefore = roles.size();
        roles.remove(new Role(role));
        if (rolesBefore >= roles.size()) {
            return Collections.singletonMap("status", "role not found");
        } else {
            userService.save(fromDb);
            return Collections.singletonMap("status", "ok");
        }
    }
}

package no.swact.action.controllers.api;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.services.RoleService;
import no.swact.action.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleRestController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleRestController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{role}/users", method = RequestMethod.GET)
    public List<User> getUsersByRole(@PathVariable String role) {
        return roleService.findByName(role.toUpperCase());
    }
}

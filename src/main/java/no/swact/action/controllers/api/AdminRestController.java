package no.swact.action.controllers.api;

import no.swact.action.models.User;
import no.swact.action.models.auth.Role;
import no.swact.action.services.RoleService;
import no.swact.action.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/secret/{token}")
    public Map<String, String> exchange(@PathVariable String token) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        List<User> all = userService.findAllAdmins();
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        String secret = environment.getProperty("action.secret");
        if (user != null && all.isEmpty() && token.equals(secret)) {
            Role admin = new Role("ADMIN");
            admin.addUser(user);
            roleService.save(admin);
            stringStringHashMap.put("status", "accepted");
            return stringStringHashMap;
        } else {
            stringStringHashMap.put("status", "denied");
            return stringStringHashMap;
        }
    }
}

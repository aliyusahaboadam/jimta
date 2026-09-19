package academy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import academy.auth.CustomUserDetailsService;
import academy.model.User;
import academy.payload.BodyMessage;
import academy.payload.LoginResponse;
import academy.utility.JwtUtil;

@RequestMapping("/v1/api")
@RestController
public class LoginController {

    private static final String ADMIN_HOME_URL  = "/admin/home";
    private static final String COACH_HOME_URL  = "/coach/home";
    private static final String PLAYER_HOME_URL = "/player/home";
    private static final String ERROR           = "error";

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

     
        
        String role = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .map(r -> r.startsWith("ROLE_") ? r.substring(5) : r)
                .findFirst()
                .orElse("");

        String redirectUrl = switch (role) {
            case "ADMIN"  -> ADMIN_HOME_URL;
            case "COACH"  -> COACH_HOME_URL;
            case "PLAYER" -> PLAYER_HOME_URL;
            default       -> ERROR;
        };

        LoginResponse response = new LoginResponse(jwt, redirectUrl);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/is-valid/{token}")
    public boolean jwtTokenValidator(@PathVariable String token, Principal principal) {
        boolean validity = false;
        try {
            validity = jwtUtil.frontEndTokenValidityChecker(token, principal.getName());
        } catch (ExpiredJwtException e) {
            // expired token -> validity stays false
        }
        return validity;
    }


}

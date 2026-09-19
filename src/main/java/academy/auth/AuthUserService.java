package academy.auth;

import java.time.LocalTime;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserService {

    private final CustomUserDetailsService userDetailsService;

    public AuthUserService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // ============================================================
    // ID RESOLVERS
    // ============================================================

    public Long authenticatedAdminId() {
        return currentUser().getAdminId();
    }

    public Long authenticatedCoachId() {
        return currentUser().getCoachId();
    }

    public Long authenticatedPlayerId() {
        return currentUser().getPlayerId();
    }

    // ============================================================
    // OPTIONAL CONVENIENCE HELPERS
    // ============================================================

    public String currentUsername() {
        return currentUser().getUsername();
    }

    public String currentRole() {
        return currentUser().getRole();
    }

    public Long currentUserId() {
        return currentUser().getId();
    }

    // ============================================================
    // WELCOME MESSAGES
    // ============================================================

    public String welcomePlayer() {
        CustomUserDetails user = currentUser();
        return getGreetingBasedOnTime() + ", " + safeName(user);
    }

    public String welcomeCoach() {
        CustomUserDetails user = currentUser();
        return getGreetingBasedOnTime() + ", Coach " + safeName(user);
    }

    public String welcomeAdmin() {
        CustomUserDetails user = currentUser();
        return getGreetingBasedOnTime() + ", Admin " + safeName(user);
    }

    // ============================================================
    // INTERNAL HELPERS
    // ============================================================

    /**
     * Loads the authenticated user from the SecurityContext.
     * Throws if there is no authenticated principal (anonymous or null).
     */
    private CustomUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("No authenticated user in the security context");
        }

        String currentUsername = authentication.getName();
        return (CustomUserDetails) userDetailsService.loadUserByUsername(currentUsername);
    }

    private String safeName(CustomUserDetails user) {
        if (user.getFirstname() != null && !user.getFirstname().isBlank()) {
            return user.getFirstname();
        }
        return user.getUsername();
    }

    private String getGreetingBasedOnTime() {
        LocalTime now = LocalTime.now();

        if (now.isBefore(LocalTime.NOON)) {
            return "Good Morning";
        } else if (now.isBefore(LocalTime.of(17, 0))) {
            return "Good Afternoon";
        } else if (now.isBefore(LocalTime.of(21, 0))) {
            return "Good Evening";
        } else {
            return "Good Night";
        }
    }
}
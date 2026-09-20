package academy.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.model.Admin;
import academy.model.Coach;
import academy.model.Player;
import academy.model.User;
import academy.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }

        Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getRole());

        if ("PLAYER".equals(user.getRole())) {
            Player player = user.getPlayer();
            if (player == null) {
                throw new UsernameNotFoundException("Entity not linked for user: " + username);
            }
            return new CustomUserDetails(player, authorities);
        }

        if ("COACH".equals(user.getRole())) {
            Coach coach = user.getCoach();
            if (coach == null) {
                throw new UsernameNotFoundException("Entity not linked for user: " + username);
            }
            return new CustomUserDetails(coach, authorities);
        }

        if ("ADMIN".equals(user.getRole())) {
            Admin admin = user.getAdmin();
            if (admin == null) {
                throw new UsernameNotFoundException("Entity not linked for user: " + username);
            }
            return new CustomUserDetails(admin, authorities);
        }

        throw new UsernameNotFoundException("Unknown role: " + user.getRole());
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        // Prefix with ROLE_ so hasRole("PLAYER") works in Spring Security
        return List.of(new SimpleGrantedAuthority(role));
    }
}
package academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import academy.model.Admin;
import academy.model.Profile;
import academy.model.User;
import academy.repository.AdminRepository;
import academy.repository.ProfileRepository;
import academy.repository.UserRepository;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private ProfileRepository profileRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByRole("ADMIN") != null) {
            return;
        }

        // 1. Admin (domain entity) first — Profile and User reference it by FK
        Admin admin = new Admin();
        admin.setFirstname("Aliyu");
        admin.setSurname("Sahabo");
        admin.setLastname("Adam");
        admin.setEmail("aliyusahaboadam@gmail.com");
        admin.setPhoneNumber("08169863672");
        admin = adminRepository.save(admin);

        // 2. Profile owning the admin_id FK
        Profile profile = new Profile.Builder()
                .setFirstname("Aliyu")
                .setSurname("Sahabo")
                .setLastname("Adam")
                .setGender("Male")
                .setPhoneNumber("08169863672")
                .setDateOfBirth("1998-11-26")
                .setAdmin(admin)
                .build();
        profile = profileRepository.save(profile);

        // 3. User owning the admin_id FK + credentials
        User user = new User();
        user.setRole("ADMIN");
        user.setUsername("aliyusahaboadam@gmail.com");
        user.setEmail("aliyusahaboadam@gmail.com");
        user.setPassword(passwordEncoder.encode("@#26111998Aa"));
        user.setAdmin(admin);
        user = userRepository.save(user);

        // 4. Sync the inverse sides (in-memory only — no extra saves needed)
        admin.setProfile(profile);
        admin.setUser(user);
    }
}
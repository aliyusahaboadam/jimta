package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import academy.dto.request.CoachRequestDto;
import academy.dto.response.CoachResponseDto;
import academy.interfaces.CoachService;
import academy.model.Coach;
import academy.model.Profile;
import academy.model.User;
import academy.repository.CoachRepository;
import academy.repository.UserRepository;
import academy.utility.IdentityGenerator;
import jakarta.transaction.Transactional;

@Service
public class CoachServiceImpl implements CoachService {

	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	

	@Override
	@Transactional
	public Coach saveCoach(CoachRequestDto dto) {

	    // 1. Generate unique username
	    String username;
	    do {
	        username = IdentityGenerator.generateCoachUsername();
	    } while (userRepository.findByUsername(username) != null);

	    // 2. Build User
	    User user = new User.Builder()
	            .setUsername(username)
	            .setPassword(passwordEncoder.encode(username))
	            .setEmail(dto.getEmail())
	            .setRole("COACH")
	            .build();

	    // 3. Build Coach
	    Coach coach = new Coach.Builder()
	            .setLicenseNo(dto.getLicenseNo())
	            .setSpecialization(dto.getSpecialization())
	            .setUser(user)
	            .build();

	    // 4. Back-reference: user -> coach
	    user.setCoachInternal(coach);
	    
	    
	    Profile p = dto.getProfile();

        Profile profile = new Profile.Builder()
                    .setFirstname(p.getFirstname())
                    .setSurname(p.getSurname())
                    .setLastname(p.getLastname())
                    .setDateOfBirth(p.getDateOfBirth())
                    .setGender(p.getGender())
                    .setPhoneNumber(p.getPhoneNumber())
                    .setCoach(coach)   // ← owner side
                    .build();
        

        // Attach profile to player
        coach.setProfile(profile);

	    // 5. Save (cascade from Coach -> User handles the insert)
	    return coachRepository.save(coach);
	}

	@Override
	public Optional<Coach> findById(Long id) {
		return coachRepository.findById(id);
	}

	@Override
	public Optional<Coach> findByLicenseNo(String licenseNo) {
		return coachRepository.findByLicenseNo(licenseNo);
	}

	@Override
	public List<Coach> getAllCoaches() {
		return coachRepository.findAll();
	}

	@Override
	public Optional<CoachResponseDto> findCoachDtoById(Long id) {
		return coachRepository.findCoachDtoById(id);
	}

	@Override
	public List<CoachResponseDto> findAllCoachDto() {
		return coachRepository.findAllCoachDto();
	}

	@Override
	public Optional<CoachResponseDto> findCoachDtoByTeamId(Long teamId) {
		return coachRepository.findCoachDtoByTeamId(teamId);
	}

	@Override
	public void deleteCoach(Long id) {
		coachRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public Coach updateCoach(Long id, CoachRequestDto dto) {
	    Coach coach = coachRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Coach not found"));
	    coach.setLicenseNo(dto.getLicenseNo());
	    coach.setSpecialization(dto.getSpecialization());
	    return coachRepository.save(coach);
	}

}

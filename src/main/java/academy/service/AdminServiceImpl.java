package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import academy.dto.request.AdminRequestDto;
import academy.dto.response.AdminResponseDto;
import academy.exception.AcademyException;
import academy.interfaces.AdminService;
import academy.model.Admin;
import academy.model.Profile;
import academy.model.User;
import academy.repository.AdminRepository;
import academy.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public Admin saveAdmin(AdminRequestDto dto) {
		
		if (userRepository.existsByEmail(dto.getEmail())) {
	        throw new AcademyException(
	            "A user with this email already exists",
	            HttpStatus.CONFLICT
	        );
	    }

	    User user = new User.Builder()
	            .setUsername(dto.getEmail())   // Admin uses email as username
	            .setPassword(passwordEncoder.encode(
	            	    dto.getPassword() != null && !dto.getPassword().isBlank()
	            	        ? dto.getPassword()
	            	        : dto.getEmail()
	            	))
	            .setEmail(dto.getEmail())
	            .setRole("ADMIN")
	            .build();

	    Admin admin = new Admin.Builder()
	            .setFirstname(dto.getFirstname())
	            .setSurname(dto.getSurname())
	            .setLastname(dto.getLastname())
	            .setEmail(dto.getEmail())
	            .setPhoneNumber(dto.getPhoneNumber())
	            .setUser(user)
	            .build();

	    user.setAdmin(admin);
	    
	    Profile p = dto.getProfile();

        Profile profile = new Profile.Builder()
                    .setFirstname(p.getFirstname())
                    .setSurname(p.getSurname())
                    .setLastname(p.getLastname())
                    .setDateOfBirth(p.getDateOfBirth())
                    .setGender(p.getGender())
                    .setPhoneNumber(p.getPhoneNumber())
                    .setAdmin(admin)   // ← owner side
                    .build();
        

        // Attach profile to player
        admin.setProfile(profile);

	    return adminRepository.save(admin);
	}


	@Override
	public Optional<Admin> findById(Long id) {
		return adminRepository.findById(id);
	}

	@Override
	public Optional<Admin> findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return adminRepository.existsByEmail(email);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<AdminResponseDto> findAdminDtoById(Long id) {
		return adminRepository.findAdminDtoById(id);
	}

	@Override
	public List<AdminResponseDto> findAllAdminDto() {
		return adminRepository.findAllAdminDto();
	}

	@Override
	@Transactional
	public void deleteAdmin(Long id) {
	    Admin admin = adminRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Admin not found"));

	    admin.removeAllForgotPasswordTokens();

	    adminRepository.delete(admin);  // cascades to user/profile
	}
	
	@Override
	@Transactional
	public Admin updateAdmin(Long id, AdminRequestDto dto) {
	    Admin admin = adminRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Admin not found"));
	    admin.setFirstname(dto.getFirstname());
	    admin.setSurname(dto.getSurname());
	    admin.setLastname(dto.getLastname());
	    admin.setPhoneNumber(dto.getPhoneNumber());
	    return adminRepository.save(admin);
	}

}

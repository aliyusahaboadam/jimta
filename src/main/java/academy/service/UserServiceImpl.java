package academy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import academy.dto.response.UserResponseDto;
import academy.interfaces.UserService;
import academy.model.User;
import academy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByRole(String role) {
		return userRepository.findByRole(role);
	}

	@Override
	public Optional<UserResponseDto> findUserDtoById(Long id) {
		return userRepository.findUserDtoById(id);
	}

}

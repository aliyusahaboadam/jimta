package academy.interfaces;

import java.util.Optional;

import academy.dto.response.UserResponseDto;
import academy.model.User;

public interface UserService {

	User saveUser(User user);

	Optional<User> findById(Long id);

	User findByUsername(String username);

	User findByEmail(String email);

	User findByRole(String role);

	Optional<UserResponseDto> findUserDtoById(Long id);

}

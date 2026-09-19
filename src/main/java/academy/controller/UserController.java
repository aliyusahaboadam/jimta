package academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.auth.AuthUserService;
import academy.dto.response.UserResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.UserService;
import academy.model.User;

@RequestMapping("/v1/api/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthUserService authUserService;


	@GetMapping("/get-by-id/{id}")
	public UserResponseDto getUserById(@PathVariable Long id) {
		return userService.findUserDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}


	@GetMapping("/get-authenticated-user")
	public UserResponseDto getAuthenticatedUser() {
		return userService.findUserDtoById(authUserService.currentUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}


	@GetMapping("/get-by-username/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}


	@GetMapping("/get-by-role/{role}")
	public User getUserByRole(@PathVariable String role) {
		return userService.findByRole(role);
	}

}

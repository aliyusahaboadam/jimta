package academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.dto.response.ProfileResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.ProfileService;

@RequestMapping("/v1/api/profile")
@RestController
public class ProfileController {

	@Autowired
	private ProfileService profileService;


	@GetMapping("/get-by-player/{playerId}")
	public ProfileResponseDto getProfileByPlayerId(@PathVariable Long playerId) {
		return profileService.findProfileDtoByPlayerId(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found for this player"));
	}


	@GetMapping("/get-by-coach/{coachId}")
	public ProfileResponseDto getProfileByCoachId(@PathVariable Long coachId) {
		return profileService.findProfileDtoByCoachId(coachId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found for this coach"));
	}


	@GetMapping("/get-by-admin/{adminId}")
	public ProfileResponseDto getProfileByAdminId(@PathVariable Long adminId) {
		return profileService.findProfileDtoByAdminId(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found for this admin"));
	}

}

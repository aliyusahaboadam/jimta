package academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.auth.AuthUserService;
import academy.dto.request.CoachRequestDto;
import academy.dto.response.CoachResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.CoachService;
import academy.model.Coach;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/coach")
@RestController
public class CoachController {

	@Autowired
	private CoachService coachService;

	@Autowired
	private AuthUserService authUserService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addCoach(@RequestBody CoachRequestDto dto) {
		coachService.saveCoach(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Coach added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-id/{id}")
	public CoachResponseDto getCoachById(@PathVariable Long id) {
		return coachService.findCoachDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
	}


	@GetMapping("/get-authenticated-coach")
	public CoachResponseDto getAuthenticatedCoach() {
		return coachService.findCoachDtoById(authUserService.authenticatedCoachId())
				.orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
	}


	@GetMapping("/get-by-license/{licenseNo}")
	public Coach getCoachByLicenseNo(@PathVariable String licenseNo) {
		return coachService.findByLicenseNo(licenseNo)
				.orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
	}


	@GetMapping("/get-by-team/{teamId}")
	public CoachResponseDto getCoachByTeamId(@PathVariable Long teamId) {
		return coachService.findCoachDtoByTeamId(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Coach not found for this team"));
	}


	@GetMapping("/get-all")
	public List<CoachResponseDto> getAllCoaches() {
		return coachService.findAllCoachDto();
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BodyMessage> deleteCoachById(@PathVariable Long id) {
		coachService.deleteCoach(id);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setId(id);
		bodyMessage.setMessage("Coach deleted successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}


	@GetMapping("/welcome")
	public String welcome() {
		return authUserService.welcomeCoach();
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BodyMessage> updateCoach(@PathVariable Long id,
	        @RequestBody CoachRequestDto dto) {
	    coachService.updateCoach(id, dto);
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setId(id);
	    bodyMessage.setMessage("Coach updated successfully");
	    return ResponseEntity.ok(bodyMessage);
	}

}

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

import academy.dto.request.TeamRequestDto;
import academy.dto.response.TeamResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.TeamService;
import academy.model.Team;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/team")
@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addTeam(@RequestBody TeamRequestDto dto) {
		teamService.saveTeam(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Team added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-id/{id}")
	public TeamResponseDto getTeamById(@PathVariable Long id) {
		return teamService.findTeamDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team not found"));
	}


	@GetMapping("/get-all")
	public List<TeamResponseDto> getAllTeams() {
		return teamService.findAllTeamDto();
	}


	@GetMapping("/get-all-with-player-count")
	public List<TeamResponseDto> getAllTeamsWithPlayerCount() {
		return teamService.findAllTeamDtoWithPlayerCount();
	}


	@GetMapping("/get-by-coach/{coachId}")
	public List<TeamResponseDto> getTeamsByCoachId(@PathVariable Long coachId) {
		return teamService.findAllTeamDtoByCoachId(coachId);
	}


	@GetMapping("/get-by-age-group/{ageGroup}")
	public List<Team> getTeamsByAgeGroup(@PathVariable String ageGroup) {
		return teamService.findAllByAgeGroup(ageGroup);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BodyMessage> deleteTeamById(@PathVariable Long id) {
		teamService.deleteTeam(id);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setId(id);
		bodyMessage.setMessage("Team deleted successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<BodyMessage> updateTeam(@PathVariable Long id,
	        @RequestBody TeamRequestDto dto) {
	    teamService.updateTeam(id, dto);
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setId(id);
	    bodyMessage.setMessage("Team updated successfully");
	    return ResponseEntity.ok(bodyMessage);
	}

}
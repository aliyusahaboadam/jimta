package academy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.dto.request.MatchRequestDto;
import academy.dto.response.MatchResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.MatchService;
import academy.model.Match;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/match")
@RestController
public class MatchController {

	@Autowired
	private MatchService matchService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addMatch(@RequestBody MatchRequestDto dto) {
		matchService.saveMatch(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Match added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-id/{id}")
	public MatchResponseDto getMatchById(@PathVariable Long id) {
		return matchService.findMatchDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Match not found"));
	}


	@GetMapping("/get-all")
	public List<Match> getAllMatches() {
		return matchService.getAllMatches();
	}


	@GetMapping("/get-by-status/{status}")
	public List<MatchResponseDto> getMatchesByStatus(@PathVariable String status) {
		return matchService.findAllMatchDtoByStatus(status);
	}


	@GetMapping("/get-by-team/{teamId}")
	public List<MatchResponseDto> getMatchesByTeamId(@PathVariable Long teamId) {
		return matchService.findAllMatchDtoByTeamId(teamId);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BodyMessage> deleteMatchById(@PathVariable Long id) {
	    matchService.deleteMatch(id);
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setId(id);
	    bodyMessage.setMessage("Match deleted successfully!");
	    return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BodyMessage> updateMatch(@PathVariable Long id, @RequestBody MatchRequestDto dto) {
	    matchService.updateMatch(id, dto);
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setId(id);
	    bodyMessage.setMessage("Match updated successfully");
	    return ResponseEntity.ok(bodyMessage);
	}

}

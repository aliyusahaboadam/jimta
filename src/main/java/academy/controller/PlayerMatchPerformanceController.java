package academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.dto.request.PlayerMatchPerformanceRequestDto;
import academy.dto.response.PlayerMatchPerformanceResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.PlayerMatchPerformanceService;
import academy.model.PlayerMatchPerformance;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/performance")
@RestController
public class PlayerMatchPerformanceController {

	@Autowired
	private PlayerMatchPerformanceService playerMatchPerformanceService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addPerformance(@RequestBody PlayerMatchPerformanceRequestDto dto) {
		playerMatchPerformanceService.savePerformance(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Performance recorded successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-match/{matchId}")
	public List<PlayerMatchPerformanceResponseDto> getPerformancesByMatchId(@PathVariable Long matchId) {
		return playerMatchPerformanceService.findAllPerformanceDtoByMatchId(matchId);
	}


	@GetMapping("/get-by-player/{playerId}")
	public List<PlayerMatchPerformance> getPerformancesByPlayerId(@PathVariable Long playerId) {
		return playerMatchPerformanceService.findAllByPlayerId(playerId);
	}


	@GetMapping("/get-by-player-and-match")
	public PlayerMatchPerformance getByPlayerIdAndMatchId(
			@RequestParam Long playerId,
			@RequestParam Long matchId) {
		return playerMatchPerformanceService.findByPlayerIdAndMatchId(playerId, matchId)
				.orElseThrow(() -> new ResourceNotFoundException("Performance not found"));
	}


	@GetMapping("/get-season-totals/{playerId}")
	public PlayerMatchPerformanceResponseDto getSeasonTotals(@PathVariable Long playerId) {
		return playerMatchPerformanceService.findSeasonTotalsDtoByPlayerId(playerId);
	}

}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.auth.AuthUserService;
import academy.dto.request.PlayerRequestDto;
import academy.dto.response.PlayerResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.PlayerService;
import academy.model.Player;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/player")
@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private AuthUserService authUserService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addPlayer(@RequestBody PlayerRequestDto dto) {
		playerService.savePlayer(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Player added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-id/{id}")
	public PlayerResponseDto getPlayerById(@PathVariable Long id) {
		return playerService.findPlayerDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found"));
	}


	@GetMapping("/get-authenticated-player")
	public PlayerResponseDto getAuthenticatedPlayer() {
		return playerService.findPlayerDtoById(authUserService.authenticatedPlayerId())
				.orElseThrow(() -> new ResourceNotFoundException("Player not found"));
	}


	@GetMapping("/get-profile/{id}")
	public PlayerResponseDto getPlayerProfileById(@PathVariable Long id) {
		return playerService.findPlayerProfileDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found"));
	}


	@GetMapping("/get-by-team/{teamId}")
	public List<PlayerResponseDto> getPlayersByTeamId(@PathVariable Long teamId) {
		return playerService.findAllPlayerDtoByTeamId(teamId);
	}


	@GetMapping("/get-by-jersey")
	public Player getPlayerByJerseyAndTeam(@RequestParam Integer jerseyNumber, @RequestParam Long teamId) {
		return playerService.findByJerseyNumberAndTeamId(jerseyNumber, teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found"));
	}


	@GetMapping("/get-all")
	public List<PlayerResponseDto> getAllPlayers() {
	    return playerService.findAllPlayerDto();
	}


	@PutMapping("/update/{id}")
	public ResponseEntity<BodyMessage> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {

		Player player = playerService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found"));

		player.setPosition(playerDetails.getPosition());
		player.setJerseyNumber(playerDetails.getJerseyNumber());
		player.setPlayerNumber(playerDetails.getPlayerNumber());
		player.setNationality(playerDetails.getNationality());
		player.setPreferredFoot(playerDetails.getPreferredFoot());
		player.setHeightCm(playerDetails.getHeightCm());
		player.setWeightKg(playerDetails.getWeightKg());
		player.setPreviousClub(playerDetails.getPreviousClub());
		player.setPhotoUrl(playerDetails.getPhotoUrl());

		playerService.savePlayer(player);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Player updated successfully");
		return ResponseEntity.ok(bodyMessage);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BodyMessage> deletePlayerById(@PathVariable Long id) {
		playerService.deletePlayer(id);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setId(id);
		bodyMessage.setMessage("Player deleted successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}


	@GetMapping("/welcome")
	public String welcome() {
		return authUserService.welcomePlayer();
	}

}

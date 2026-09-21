package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.PlayerRequestDto;
import academy.dto.response.PlayerResponseDto;
import academy.model.Player;

public interface PlayerService {

	Player savePlayer(Player player);

	Optional<Player> findById(Long id);

	List<Player> getAllPlayers();

	List<Player> findAllByTeamId(Long teamId);

	Optional<Player> findByJerseyNumberAndTeamId(Integer jerseyNumber, Long teamId);

	Optional<PlayerResponseDto> findPlayerDtoById(Long id);

	List<PlayerResponseDto> findAllPlayerDtoByTeamId(Long teamId);

	Optional<PlayerResponseDto> findPlayerProfileDtoById(Long id);

	void deletePlayer(Long id);
	
	Player savePlayer(PlayerRequestDto dto);
	
	List<PlayerResponseDto> findAllPlayerDto();

}

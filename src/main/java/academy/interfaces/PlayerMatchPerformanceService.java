package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.PlayerMatchPerformanceRequestDto;
import academy.dto.request.PlayerRequestDto;
import academy.dto.response.PlayerMatchPerformanceResponseDto;
import academy.model.Player;
import academy.model.PlayerMatchPerformance;

public interface PlayerMatchPerformanceService {

	PlayerMatchPerformance savePerformance(PlayerMatchPerformanceRequestDto dto);

	Optional<PlayerMatchPerformance> findById(Long id);

	List<PlayerMatchPerformance> findAllByMatchId(Long matchId);

	List<PlayerMatchPerformance> findAllByPlayerId(Long playerId);

	Optional<PlayerMatchPerformance> findByPlayerIdAndMatchId(Long playerId, Long matchId);

	List<PlayerMatchPerformanceResponseDto> findAllPerformanceDtoByMatchId(Long matchId);

	PlayerMatchPerformanceResponseDto findSeasonTotalsDtoByPlayerId(Long playerId);
	
	

}

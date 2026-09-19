package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.PlayerMatchPerformanceResponseDto;
import academy.model.PlayerMatchPerformance;

public interface PlayerMatchPerformanceRepository extends JpaRepository<PlayerMatchPerformance, Long> {

	List<PlayerMatchPerformance> findAllByMatch_Id(Long matchId);

	List<PlayerMatchPerformance> findAllByPlayer_Id(Long playerId);

	Optional<PlayerMatchPerformance> findByPlayer_IdAndMatch_Id(Long playerId, Long matchId);

	@Query("SELECT new academy.dto.response.PlayerMatchPerformanceResponseDto("
			+ "SUM(pf.goals), SUM(pf.assists), SUM(pf.yellowCards), SUM(pf.redCards), SUM(pf.minutesPlayed)) "
			+ "FROM PlayerMatchPerformance pf WHERE pf.player.id = :playerId")
	PlayerMatchPerformanceResponseDto findSeasonTotalsDtoByPlayerId(@Param("playerId") Long playerId);
	
	@Query("SELECT new academy.dto.response.PlayerMatchPerformanceResponseDto("
	        + "pf.id, pf.player.id, pf.player.team.id, pr.firstname, pr.surname, "
	        + "pf.goals, pf.assists, pf.yellowCards, pf.redCards, pf.minutesPlayed, pf.started) "
	        + "FROM PlayerMatchPerformance pf "
	        + "LEFT JOIN Profile pr ON pr.player.id = pf.player.id "
	        + "WHERE pf.match.id = :matchId")
	List<PlayerMatchPerformanceResponseDto> findAllPerformanceDtoByMatchId(@Param("matchId") Long matchId);

}

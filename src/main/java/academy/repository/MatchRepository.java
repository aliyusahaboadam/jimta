package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.MatchResponseDto;
import academy.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findAllByStatus(String status);

	@Query("SELECT m FROM Match m WHERE m.homeTeam.id = :teamId OR m.awayTeam.id = :teamId")
	List<Match> findAllByTeamId(@Param("teamId") Long teamId);

	@EntityGraph(attributePaths = {}) // don't pull in performances for a plain list
	List<Match> findAll();

	@Query("SELECT new academy.dto.response.MatchResponseDto(m.id, m.matchDate, m.venue, m.status, "
			+ "ht.name, at.name, m.homeScore, m.awayScore) "
			+ "FROM Match m JOIN m.homeTeam ht JOIN m.awayTeam at "
			+ "WHERE m.id = :id")
	Optional<MatchResponseDto> findMatchDtoById(@Param("id") Long id);

	@Query("SELECT new academy.dto.response.MatchResponseDto(m.id, m.matchDate, m.venue, m.status, "
			+ "ht.name, at.name, m.homeScore, m.awayScore) "
			+ "FROM Match m JOIN m.homeTeam ht JOIN m.awayTeam at "
			+ "WHERE ht.id = :teamId OR at.id = :teamId "
			+ "ORDER BY m.matchDate DESC")
	List<MatchResponseDto> findAllMatchDtoByTeamId(@Param("teamId") Long teamId);

	@Query("SELECT new academy.dto.response.MatchResponseDto(m.id, m.matchDate, m.venue, m.status, "
			+ "ht.name, at.name, m.homeScore, m.awayScore) "
			+ "FROM Match m JOIN m.homeTeam ht JOIN m.awayTeam at "
			+ "WHERE m.status = :status "
			+ "ORDER BY m.matchDate ASC")
	List<MatchResponseDto> findAllMatchDtoByStatus(@Param("status") String status);
	
	
	@Query("SELECT new academy.dto.response.MatchResponseDto("
	        + "m.id, m.matchDate, m.venue, m.status, "
	        + "ht.name, at.name, m.homeScore, m.awayScore) "
	        + "FROM Match m "
	        + "JOIN m.homeTeam ht "
	        + "JOIN m.awayTeam at "
	        + "ORDER BY m.matchDate DESC")
	List<MatchResponseDto> findAllMatchDto();

}

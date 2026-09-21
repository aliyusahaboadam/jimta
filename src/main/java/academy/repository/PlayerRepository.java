package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.PlayerResponseDto;
import academy.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findAllByTeam_Id(Long teamId);

	Optional<Player> findByJerseyNumberAndTeam_Id(Integer jerseyNumber, Long teamId);

	@EntityGraph(attributePaths = {}) // don't pull in performances/user for a plain list
	List<Player> findAll();

	@Query("SELECT new academy.dto.response.PlayerResponseDto("
	        + "p.id, pr.firstname, pr.surname, pr.lastname, "
	        + "p.position, p.jerseyNumber, "
	        + "p.nationality, p.preferredFoot, p.heightCm, p.weightKg, "
	        + "p.playerNumber, p.previousClub, "
	        + "t.id, t.name) "
	        + "FROM Player p "
	        + "LEFT JOIN Profile pr ON pr.player.id = p.id "
	        + "LEFT JOIN Team t ON t.id = p.team.id "
	        + "WHERE p.id = :id")
	Optional<PlayerResponseDto> findPlayerDtoById(@Param("id") Long id);

	@Query("SELECT new academy.dto.response.PlayerResponseDto(p.id, pr.firstname, pr.surname, pr.lastname, "
			+ "p.position, p.jerseyNumber) "
			+ "FROM Player p LEFT JOIN Profile pr ON pr.player.id = p.id "
			+ "WHERE p.team.id = :teamId")
	List<PlayerResponseDto> findAllPlayerDtoByTeamId(@Param("teamId") Long teamId);

	@Query("SELECT new academy.dto.response.PlayerResponseDto(p.id, pr.firstname, pr.surname, pr.lastname, "
			+ "p.position, p.jerseyNumber, p.nationality, p.preferredFoot, p.heightCm, p.weightKg) "
			+ "FROM Player p LEFT JOIN Profile pr ON pr.player.id = p.id "
			+ "WHERE p.id = :id")
	Optional<PlayerResponseDto> findPlayerProfileDtoById(@Param("id") Long id);
	
	@Query("SELECT new academy.dto.response.PlayerResponseDto("
	        + "p.id, pr.firstname, pr.surname, pr.lastname, "
	        + "p.position, p.jerseyNumber, "
	        + "p.nationality, p.preferredFoot, p.heightCm, p.weightKg, "
	        + "p.playerNumber, p.previousClub, "
	        + "p.photoUrl, t.id, t.name) "
	        + "FROM Player p "
	        + "LEFT JOIN Profile pr ON pr.player.id = p.id "
	        + "LEFT JOIN Team t ON t.id = p.team.id")
	List<PlayerResponseDto> findAllPlayerDto();

}

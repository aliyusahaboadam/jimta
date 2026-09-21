package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.TeamResponseDto;
import academy.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findAllByCoach_Id(Long coachId);

	List<Team> findAllByAgeGroup(String ageGroup);

	@EntityGraph(attributePaths = {}) // don't pull in players/matches for a plain list
	List<Team> findAll();

	@Query("SELECT new academy.dto.response.TeamResponseDto("
	        + "t.id, t.name, t.ageGroup, t.division, "
	        + "c.id, CONCAT(pr.firstname, ' ', pr.surname)) "
	        + "FROM Team t "
	        + "LEFT JOIN t.coach c "
	        + "LEFT JOIN Profile pr ON pr.coach.id = c.id "
	        + "WHERE t.id = :id")
	Optional<TeamResponseDto> findTeamDtoById(@Param("id") Long id);

	@Query("SELECT new academy.dto.response.TeamResponseDto(t.id, t.name, t.ageGroup, t.division) "
			+ "FROM Team t")
	List<TeamResponseDto> findAllTeamDto();

	@Query("SELECT new academy.dto.response.TeamResponseDto(t.id, t.name, t.ageGroup, t.division) "
			+ "FROM Team t WHERE t.coach.id = :coachId")
	List<TeamResponseDto> findAllTeamDtoByCoachId(@Param("coachId") Long coachId);

	@Query("SELECT new academy.dto.response.TeamResponseDto("
	        + "t.id, t.name, t.ageGroup, t.division, SIZE(t.players), "
	        + "c.id, CONCAT(pr.firstname, ' ', pr.surname)) "
	        + "FROM Team t "
	        + "LEFT JOIN t.coach c "
	        + "LEFT JOIN Profile pr ON pr.coach.id = c.id")
	List<TeamResponseDto> findAllTeamDtoWithPlayerCount();
	
	

}
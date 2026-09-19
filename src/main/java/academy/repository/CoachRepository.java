package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.CoachResponseDto;
import academy.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

	Optional<Coach> findByLicenseNo(String licenseNo);

	@EntityGraph(attributePaths = {}) // don't pull in teams for a plain list
	List<Coach> findAll();

	@Query("SELECT new academy.dto.response.CoachResponseDto("
	        + "c.id, c.licenseNo, c.specialization, pr.firstname, pr.surname) "
	        + "FROM Coach c LEFT JOIN Profile pr ON pr.coach.id = c.id")
	List<CoachResponseDto> findAllCoachDto();

	@Query("SELECT new academy.dto.response.CoachResponseDto("
	        + "c.id, c.licenseNo, c.specialization, pr.firstname, pr.surname) "
	        + "FROM Coach c LEFT JOIN Profile pr ON pr.coach.id = c.id "
	        + "WHERE c.id = :id")
	Optional<CoachResponseDto> findCoachDtoById(@Param("id") Long id);

	@Query("SELECT new academy.dto.response.CoachResponseDto(c.id, c.licenseNo, c.specialization) "
			+ "FROM Coach c WHERE c.id IN (SELECT t.coach.id FROM Team t WHERE t.id = :teamId)")
	Optional<CoachResponseDto> findCoachDtoByTeamId(@Param("teamId") Long teamId);
	
	

}

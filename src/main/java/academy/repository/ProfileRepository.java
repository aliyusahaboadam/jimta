package academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.ProfileResponseDto;
import academy.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findByPlayer_Id(Long playerId);

	Optional<Profile> findByCoach_Id(Long coachId);

	Optional<Profile> findByAdmin_Id(Long adminId);

	@Query("SELECT new academy.dto.response.ProfileResponseDto("
	        + "p.id, p.firstname, p.surname, p.lastname, "
	        + "p.dateOfBirth, p.gender, p.phoneNumber, "
	        + "u.username, u.email) "
	        + "FROM Profile p "
	        + "LEFT JOIN User u ON u.player.id = p.player.id "
	        + "WHERE p.player.id = :playerId")
	Optional<ProfileResponseDto> findProfileDtoByPlayerId(@Param("playerId") Long playerId);


	@Query("SELECT new academy.dto.response.ProfileResponseDto("
	        + "p.id, p.firstname, p.surname, p.lastname, "
	        + "p.dateOfBirth, p.gender, p.phoneNumber, "
	        + "u.username, u.email) "
	        + "FROM Profile p "
	        + "LEFT JOIN User u ON u.coach.id = p.coach.id "
	        + "WHERE p.coach.id = :coachId")
	Optional<ProfileResponseDto> findProfileDtoByCoachId(@Param("coachId") Long coachId);


	@Query("SELECT new academy.dto.response.ProfileResponseDto("
	        + "p.id, p.firstname, p.surname, p.lastname, "
	        + "p.dateOfBirth, p.gender, p.phoneNumber, "
	        + "u.username, u.email) "
	        + "FROM Profile p "
	        + "LEFT JOIN User u ON u.admin.id = p.admin.id "
	        + "WHERE p.admin.id = :adminId")
	Optional<ProfileResponseDto> findProfileDtoByAdminId(@Param("adminId") Long adminId);

}

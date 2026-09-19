package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.TrainingResponseDto;
import academy.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findAllByTeam_Id(Long teamId);

    @Query("SELECT new academy.dto.response.TrainingResponseDto("
            + "t.id, t.title, t.description, t.trainingDate, t.startTime, t.endTime, t.venue, "
            + "t.team.id, t.team.name, "
            + "t.coach.id, CONCAT(cpr.firstname, ' ', cpr.surname)) "
            + "FROM Training t "
            + "LEFT JOIN t.coach c "
            + "LEFT JOIN Profile cpr ON cpr.coach.id = c.id "
            + "WHERE t.id = :id")
    Optional<TrainingResponseDto> findTrainingDtoById(@Param("id") Long id);

    @Query("SELECT new academy.dto.response.TrainingResponseDto("
            + "t.id, t.title, t.description, t.trainingDate, t.startTime, t.endTime, t.venue, "
            + "t.team.id, t.team.name, "
            + "t.coach.id, CONCAT(cpr.firstname, ' ', cpr.surname)) "
            + "FROM Training t "
            + "LEFT JOIN t.coach c "
            + "LEFT JOIN Profile cpr ON cpr.coach.id = c.id "
            + "ORDER BY t.trainingDate DESC, t.startTime DESC")
    List<TrainingResponseDto> findAllTrainingDto();

    @Query("SELECT new academy.dto.response.TrainingResponseDto("
            + "t.id, t.title, t.description, t.trainingDate, t.startTime, t.endTime, t.venue, "
            + "t.team.id, t.team.name, "
            + "t.coach.id, CONCAT(cpr.firstname, ' ', cpr.surname)) "
            + "FROM Training t "
            + "LEFT JOIN t.coach c "
            + "LEFT JOIN Profile cpr ON cpr.coach.id = c.id "
            + "WHERE t.team.id = :teamId "
            + "ORDER BY t.trainingDate DESC, t.startTime DESC")
    List<TrainingResponseDto> findAllTrainingDtoByTeamId(@Param("teamId") Long teamId);

    @Query("SELECT new academy.dto.response.TrainingResponseDto("
            + "t.id, t.title, t.description, t.trainingDate, t.startTime, t.endTime, t.venue, "
            + "t.team.id, t.team.name, "
            + "t.coach.id, CONCAT(cpr.firstname, ' ', cpr.surname)) "
            + "FROM Training t "
            + "LEFT JOIN t.coach c "
            + "LEFT JOIN Profile cpr ON cpr.coach.id = c.id "
            + "WHERE t.coach.id = :coachId "
            + "ORDER BY t.trainingDate DESC, t.startTime DESC")
    List<TrainingResponseDto> findAllTrainingDtoByCoachId(@Param("coachId") Long coachId);

}
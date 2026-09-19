package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.TrainingRequestDto;
import academy.dto.response.TrainingResponseDto;
import academy.model.Training;

public interface TrainingService {

    // ----- Admin operations (any team) -----
    Training saveTraining(TrainingRequestDto dto);

    Training updateTraining(Long id, TrainingRequestDto dto);

    void deleteTraining(Long id);

    // ----- Coach operations (own teams only) -----
    Training saveTrainingForCoach(Long coachId, TrainingRequestDto dto);

    Training updateTrainingForCoach(Long coachId, Long trainingId, TrainingRequestDto dto);

    void deleteTrainingForCoach(Long coachId, Long trainingId);

    // ----- Shared reads -----
    Optional<Training> findById(Long id);

    List<Training> getAllTrainings();

    Optional<TrainingResponseDto> findTrainingDtoById(Long id);

    List<TrainingResponseDto> findAllTrainingDto();

    List<TrainingResponseDto> findAllTrainingDtoByTeamId(Long teamId);

    List<TrainingResponseDto> findAllTrainingDtoByCoachId(Long coachId);

}
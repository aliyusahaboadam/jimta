package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.request.TrainingRequestDto;
import academy.dto.response.TrainingResponseDto;
import academy.interfaces.TrainingService;
import academy.model.Coach;
import academy.model.Team;
import academy.model.Training;
import academy.repository.CoachRepository;
import academy.repository.TeamRepository;
import academy.repository.TrainingRepository;
import jakarta.transaction.Transactional;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CoachRepository coachRepository;


    // ============================================================
    //  ADMIN OPERATIONS — any team
    // ============================================================

    @Override
    @Transactional
    public Training saveTraining(TrainingRequestDto dto) {

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Coach coach = null;
        if (dto.getCoachId() != null) {
            coach = coachRepository.findById(dto.getCoachId())
                    .orElseThrow(() -> new RuntimeException("Coach not found"));
        }

        Training training = new Training.Builder()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setTrainingDate(dto.getTrainingDate())
                .setStartTime(dto.getStartTime())
                .setEndTime(dto.getEndTime())
                .setVenue(dto.getVenue())
                .setTeam(team)
                .setCoach(coach)
                .build();

        return trainingRepository.save(training);
    }


    @Override
    @Transactional
    public Training updateTraining(Long id, TrainingRequestDto dto) {

        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Coach coach = null;
        if (dto.getCoachId() != null) {
            coach = coachRepository.findById(dto.getCoachId())
                    .orElseThrow(() -> new RuntimeException("Coach not found"));
        }

        training.setTitle(dto.getTitle());
        training.setDescription(dto.getDescription());
        training.setTrainingDate(dto.getTrainingDate());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setVenue(dto.getVenue());
        training.setTeam(team);
        training.setCoach(coach);

        return trainingRepository.save(training);
    }


    @Override
    @Transactional
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }


    // ============================================================
    //  COACH OPERATIONS — own teams only
    // ============================================================

    @Override
    @Transactional
    public Training saveTrainingForCoach(Long coachId, TrainingRequestDto dto) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        assertCoachOwnsTeam(coachId, team);

        Training training = new Training.Builder()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setTrainingDate(dto.getTrainingDate())
                .setStartTime(dto.getStartTime())
                .setEndTime(dto.getEndTime())
                .setVenue(dto.getVenue())
                .setTeam(team)
                .setCoach(coach)   // coach runs their own session by default
                .build();

        return trainingRepository.save(training);
    }


    @Override
    @Transactional
    public Training updateTrainingForCoach(Long coachId, Long trainingId, TrainingRequestDto dto) {

        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        assertCoachOwnsTeam(coachId, training.getTeam());

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        assertCoachOwnsTeam(coachId, team);

        training.setTitle(dto.getTitle());
        training.setDescription(dto.getDescription());
        training.setTrainingDate(dto.getTrainingDate());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setVenue(dto.getVenue());
        training.setTeam(team);

        return trainingRepository.save(training);
    }


    @Override
    @Transactional
    public void deleteTrainingForCoach(Long coachId, Long trainingId) {

        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        assertCoachOwnsTeam(coachId, training.getTeam());

        trainingRepository.delete(training);
    }


    // ============================================================
    //  READS
    // ============================================================

    @Override
    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Optional<TrainingResponseDto> findTrainingDtoById(Long id) {
        return trainingRepository.findTrainingDtoById(id);
    }

    @Override
    public List<TrainingResponseDto> findAllTrainingDto() {
        return trainingRepository.findAllTrainingDto();
    }

    @Override
    public List<TrainingResponseDto> findAllTrainingDtoByTeamId(Long teamId) {
        return trainingRepository.findAllTrainingDtoByTeamId(teamId);
    }

    @Override
    public List<TrainingResponseDto> findAllTrainingDtoByCoachId(Long coachId) {
        return trainingRepository.findAllTrainingDtoByCoachId(coachId);
    }


    // ============================================================
    //  HELPER
    // ============================================================

    private void assertCoachOwnsTeam(Long coachId, Team team) {
        if (team == null) {
            throw new RuntimeException("Team not found");
        }
        if (team.getCoach() == null
                || team.getCoach().getId() != coachId) {
            throw new RuntimeException("You can only manage trainings for teams you run");
        }
    }

}
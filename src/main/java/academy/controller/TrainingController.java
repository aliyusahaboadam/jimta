package academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.auth.AuthUserService;
import academy.dto.request.TrainingRequestDto;
import academy.dto.response.TrainingResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.TrainingService;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/training")
@RestController
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private AuthUserService authUserService;


    // ============================================================
    //  ADMIN ENDPOINTS
    // ============================================================

    @PostMapping("/add")
    public ResponseEntity<BodyMessage> addTraining(@RequestBody TrainingRequestDto dto) {
        trainingService.saveTraining(dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setMessage("Training added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
    }


    @GetMapping("/get-all")
    public List<TrainingResponseDto> getAllTrainings() {
        return trainingService.findAllTrainingDto();
    }


    @GetMapping("/get-by-id/{id}")
    public TrainingResponseDto getTrainingById(@PathVariable Long id) {
        return trainingService.findTrainingDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BodyMessage> updateTraining(@PathVariable Long id,
                                                      @RequestBody TrainingRequestDto dto) {
        trainingService.updateTraining(id, dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Training updated successfully");
        return ResponseEntity.ok(bodyMessage);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BodyMessage> deleteTrainingById(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Training deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
    }


    // ============================================================
    //  COACH ENDPOINTS — scope-limited to the authenticated coach
    // ============================================================

    @PostMapping("/coach/add")
    public ResponseEntity<BodyMessage> addTrainingAsCoach(@RequestBody TrainingRequestDto dto) {
        Long coachId = authUserService.authenticatedCoachId();
        trainingService.saveTrainingForCoach(coachId, dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setMessage("Training added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
    }


    @PutMapping("/coach/update/{id}")
    public ResponseEntity<BodyMessage> updateTrainingAsCoach(@PathVariable Long id,
                                                             @RequestBody TrainingRequestDto dto) {
        Long coachId = authUserService.authenticatedCoachId();
        trainingService.updateTrainingForCoach(coachId, id, dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Training updated successfully");
        return ResponseEntity.ok(bodyMessage);
    }


    @DeleteMapping("/coach/delete/{id}")
    public ResponseEntity<BodyMessage> deleteTrainingAsCoach(@PathVariable Long id) {
        Long coachId = authUserService.authenticatedCoachId();
        trainingService.deleteTrainingForCoach(coachId, id);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Training deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
    }


    // ============================================================
    //  SHARED READS
    // ============================================================

    @GetMapping("/get-by-authenticated-coach")
    public List<TrainingResponseDto> getTrainingsForAuthenticatedCoach() {
        return trainingService.findAllTrainingDtoByCoachId(
                authUserService.authenticatedCoachId());
    }


    @GetMapping("/get-by-team/{teamId}")
    public List<TrainingResponseDto> getTrainingsByTeam(@PathVariable Long teamId) {
        return trainingService.findAllTrainingDtoByTeamId(teamId);
    }

}
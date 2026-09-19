package academy.controller;

import java.util.List;

import org.aspectj.bridge.AbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import academy.dto.request.ActivityRequestDto;
import academy.dto.response.ActivityResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.ActivityService;
import academy.payload.BodyMessage;
import academy.utility.S3Util;

@RequestMapping("/v1/api/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    private static final long MAX_PHOTO_SIZE = 1_048_576L; // 1 MB


    // ---------- CRUD ----------

    @PostMapping("/add")
    public ResponseEntity<BodyMessage> addActivity(@RequestBody ActivityRequestDto dto) {
        activityService.saveActivity(dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setMessage("Activity added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
    }


    @GetMapping("/get-all")
    public List<ActivityResponseDto> getAllActivities() {
        return activityService.findAllActivityDto();
    }


    @GetMapping("/get-by-id/{id}")
    public ActivityResponseDto getActivityById(@PathVariable Long id) {
        return activityService.findActivityDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
    }


    @GetMapping("/get-by-team/{teamId}")
    public List<ActivityResponseDto> getActivitiesByTeam(@PathVariable Long teamId) {
        return activityService.findAllActivityDtoByTeamId(teamId);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BodyMessage> updateActivity(@PathVariable Long id,
                                                      @RequestBody ActivityRequestDto dto) {
        activityService.updateActivity(id, dto);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Activity updated successfully");
        return ResponseEntity.ok(bodyMessage);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BodyMessage> deleteActivityById(@PathVariable Long id) {
        activityService.deleteActivity(id);
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setId(id);
        bodyMessage.setMessage("Activity deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
    }


    // ---------- Filtered views ----------

    @GetMapping("/get-videos")
    public List<ActivityResponseDto> getVideoActivities() {
        return activityService.findAllVideoActivities();
    }


    @GetMapping("/get-images")
    public List<ActivityResponseDto> getImageOnlyActivities() {
        return activityService.findAllImageOnlyActivities();
    }


    @GetMapping("/get-videos-by-team/{teamId}")
    public List<ActivityResponseDto> getVideoActivitiesByTeam(@PathVariable Long teamId) {
        return activityService.findAllVideoActivitiesByTeamId(teamId);
    }


    @GetMapping("/get-images-by-team/{teamId}")
    public List<ActivityResponseDto> getImageOnlyActivitiesByTeam(@PathVariable Long teamId) {
        return activityService.findAllImageOnlyActivitiesByTeamId(teamId);
    }


    // ---------- Photo Upload ----------

    @PostMapping("/upload-photo")
    public ResponseEntity<BodyMessage> uploadPhoto(
            @RequestParam("image") MultipartFile multipartFile) {

        try {
            ResponseEntity<BodyMessage> validationError = validatePhoto(multipartFile);
            if (validationError != null) return validationError;

            String uniqueFileName = buildUniqueFileName(multipartFile);
            S3Util.uploadFile(uniqueFileName, multipartFile.getInputStream());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BodyMessage(uniqueFileName));

        } catch (AbortException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new BodyMessage("Failed to upload file to storage"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BodyMessage("Failed to upload photo: " + e.getMessage()));
        }
    }


    // ---------- Helpers ----------

    private ResponseEntity<BodyMessage> validatePhoto(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new BodyMessage("No file provided"));
        }
        if (file.getSize() > MAX_PHOTO_SIZE) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(new BodyMessage("Photo must be 1 MB or smaller"));
        }
        String contentType = file.getContentType();
        if (!isValidImageType(contentType)) {
            return ResponseEntity.badRequest()
                    .body(new BodyMessage("Invalid file type. Only images are allowed."));
        }
        return null;
    }


    private boolean isValidImageType(String contentType) {
        return contentType != null && (
                contentType.equals("image/jpeg") ||
                contentType.equals("image/jpg")  ||
                contentType.equals("image/png")  ||
                contentType.equals("image/gif")  ||
                contentType.equals("image/webp")
        );
    }


    private String buildUniqueFileName(MultipartFile file) {
        String original = StringUtils.cleanPath(
                file.getOriginalFilename() == null
                        ? "activity-photo"
                        : file.getOriginalFilename());
        return "activities/" + System.currentTimeMillis() + "_" + original;
    }

}
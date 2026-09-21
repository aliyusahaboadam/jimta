package academy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.request.ActivityRequestDto;
import academy.dto.response.ActivityResponseDto;
import academy.interfaces.ActivityService;
import academy.model.Activity;
import academy.model.Team;
import academy.repository.ActivityRepository;
import academy.repository.TeamRepository;
import jakarta.transaction.Transactional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TeamRepository teamRepository;


    // ---------- Write operations ----------

    @Override
    @Transactional
    public Activity saveActivity(ActivityRequestDto dto) {

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Activity activity = new Activity.Builder()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setActivityDate(dto.getActivityDate())
                .setLocation(dto.getLocation())
                .setPhotoUrls(dto.getPhotoUrls() != null
                        ? dto.getPhotoUrls()
                        : new java.util.ArrayList<>())
                .setFacebookUrl(dto.getFacebookUrl())
                .setInstagramUrl(dto.getInstagramUrl())
                .setTiktokUrl(dto.getTiktokUrl())
                .setTeam(team)
                .build();

        return activityRepository.save(activity);
    }


    @Override
    @Transactional
    public Activity updateActivity(Long id, ActivityRequestDto dto) {

        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        activity.setTitle(dto.getTitle());
        activity.setDescription(dto.getDescription());
        activity.setActivityDate(dto.getActivityDate());
        activity.setLocation(dto.getLocation());
        activity.setPhotoUrls(dto.getPhotoUrls() != null
                ? dto.getPhotoUrls()
                : new java.util.ArrayList<>());
        activity.setFacebookUrl(dto.getFacebookUrl());
        activity.setInstagramUrl(dto.getInstagramUrl());
        activity.setTiktokUrl(dto.getTiktokUrl());
        activity.setTeam(team);

        return activityRepository.save(activity);
    }


    // ---------- Read operations ----------

    @Override
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Optional<ActivityResponseDto> findActivityDtoById(Long id) {
        return activityRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<ActivityResponseDto> findAllActivityDto() {
        return activityRepository.findAllByOrderByActivityDateDescIdDesc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponseDto> findAllActivityDtoByTeamId(Long teamId) {
        return activityRepository.findAllByTeam_IdOrderByActivityDateDescIdDesc(teamId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponseDto> findAllVideoActivities() {
        return activityRepository.findAllByOrderByActivityDateDescIdDesc()
                .stream()
                .filter(this::hasAnyVideo)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponseDto> findAllImageOnlyActivities() {
        return activityRepository.findAllByOrderByActivityDateDescIdDesc()
                .stream()
                .filter(this::hasAnyPhoto)              // NEW
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponseDto> findAllVideoActivitiesByTeamId(Long teamId) {
        return activityRepository.findAllByTeam_IdOrderByActivityDateDescIdDesc(teamId)
                .stream()
                .filter(this::hasAnyVideo)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponseDto> findAllImageOnlyActivitiesByTeamId(Long teamId) {
        return activityRepository.findAllByTeam_IdOrderByActivityDateDescIdDesc(teamId)
                .stream()
                .filter(this::hasAnyPhoto)              // NEW — includes photos+social
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }


    // ---------- Helpers ----------

    private boolean hasAnyVideo(Activity a) {
        return isNotBlank(a.getFacebookUrl())
                || isNotBlank(a.getInstagramUrl())
                || isNotBlank(a.getTiktokUrl());
    }

    private boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private ActivityResponseDto toDto(Activity a) {
        return new ActivityResponseDto(
                a.getId(),
                a.getTitle(),
                a.getDescription(),
                a.getActivityDate(),
                a.getLocation(),
                new java.util.ArrayList<>(a.getPhotoUrls()),
                a.getFacebookUrl(),
                a.getInstagramUrl(),
                a.getTiktokUrl(),
                a.getTeam() != null ? a.getTeam().getId() : null,
                a.getTeam() != null ? a.getTeam().getName() : null
        );
    }
    
    private boolean hasAnyPhoto(Activity a) {
        return a.getPhotoUrls() != null && !a.getPhotoUrls().isEmpty();
    }

}
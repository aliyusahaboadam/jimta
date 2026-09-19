package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.ActivityRequestDto;
import academy.dto.response.ActivityResponseDto;
import academy.model.Activity;

public interface ActivityService {

    Activity saveActivity(ActivityRequestDto dto);

    Activity updateActivity(Long id, ActivityRequestDto dto);

    Optional<Activity> findById(Long id);

    List<Activity> getAllActivities();

    Optional<ActivityResponseDto> findActivityDtoById(Long id);

    List<ActivityResponseDto> findAllActivityDto();

    List<ActivityResponseDto> findAllActivityDtoByTeamId(Long teamId);

    List<ActivityResponseDto> findAllVideoActivities();

    List<ActivityResponseDto> findAllImageOnlyActivities();

    List<ActivityResponseDto> findAllVideoActivitiesByTeamId(Long teamId);

    List<ActivityResponseDto> findAllImageOnlyActivitiesByTeamId(Long teamId);

    void deleteActivity(Long id);

}
package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.CoachRequestDto;
import academy.dto.response.CoachResponseDto;
import academy.model.Coach;

public interface CoachService {

	

	Optional<Coach> findById(Long id);

	Optional<Coach> findByLicenseNo(String licenseNo);

	List<Coach> getAllCoaches();

	Optional<CoachResponseDto> findCoachDtoById(Long id);

	List<CoachResponseDto> findAllCoachDto();

	Optional<CoachResponseDto> findCoachDtoByTeamId(Long teamId);

	void deleteCoach(Long id);
	
	Coach saveCoach(CoachRequestDto dto);
	
	Coach updateCoach(Long id, CoachRequestDto dto);

}

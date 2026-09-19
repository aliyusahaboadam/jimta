package academy.interfaces;

import java.util.Optional;

import academy.dto.response.ProfileResponseDto;
import academy.model.Profile;

public interface ProfileService {

	

	Optional<Profile> findById(Long id);

	Optional<Profile> findByPlayerId(Long playerId);

	Optional<Profile> findByCoachId(Long coachId);

	Optional<Profile> findByAdminId(Long adminId);

	Optional<ProfileResponseDto> findProfileDtoByPlayerId(Long playerId);

	Optional<ProfileResponseDto> findProfileDtoByCoachId(Long coachId);

	Optional<ProfileResponseDto> findProfileDtoByAdminId(Long adminId);

}

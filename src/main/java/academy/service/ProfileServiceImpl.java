package academy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.response.ProfileResponseDto;
import academy.interfaces.ProfileService;
import academy.model.Profile;
import academy.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;



	@Override
	public Optional<Profile> findById(Long id) {
		return profileRepository.findById(id);
	}

	@Override
	public Optional<Profile> findByPlayerId(Long playerId) {
		return profileRepository.findByPlayer_Id(playerId);
	}

	@Override
	public Optional<Profile> findByCoachId(Long coachId) {
		return profileRepository.findByCoach_Id(coachId);
	}

	@Override
	public Optional<Profile> findByAdminId(Long adminId) {
		return profileRepository.findByAdmin_Id(adminId);
	}

	@Override
	public Optional<ProfileResponseDto> findProfileDtoByPlayerId(Long playerId) {
		return profileRepository.findProfileDtoByPlayerId(playerId);
	}

	@Override
	public Optional<ProfileResponseDto> findProfileDtoByCoachId(Long coachId) {
		return profileRepository.findProfileDtoByCoachId(coachId);
	}

	@Override
	public Optional<ProfileResponseDto> findProfileDtoByAdminId(Long adminId) {
		return profileRepository.findProfileDtoByAdminId(adminId);
	}

}

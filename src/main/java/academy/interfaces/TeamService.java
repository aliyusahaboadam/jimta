package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.TeamRequestDto;
import academy.dto.response.TeamResponseDto;
import academy.model.Team;

public interface TeamService {

	Team saveTeam(TeamRequestDto dto);

	Optional<Team> findById(Long id);

	List<Team> getAllTeams();

	List<Team> findAllByCoachId(Long coachId);

	List<Team> findAllByAgeGroup(String ageGroup);

	Optional<TeamResponseDto> findTeamDtoById(Long id);

	List<TeamResponseDto> findAllTeamDto();

	List<TeamResponseDto> findAllTeamDtoByCoachId(Long coachId);

	List<TeamResponseDto> findAllTeamDtoWithPlayerCount();
	
	void deleteTeam(Long id);
	
	Team updateTeam(Long id, TeamRequestDto dto);

}

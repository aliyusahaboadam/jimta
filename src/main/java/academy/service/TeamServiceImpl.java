package academy.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.request.TeamRequestDto;
import academy.dto.response.TeamResponseDto;
import academy.interfaces.TeamService;
import academy.model.Coach;
import academy.model.Match;
import academy.model.Player;
import academy.model.Team;
import academy.repository.ActivityRepository;
import academy.repository.CoachRepository;
import academy.repository.MatchRepository;
import academy.repository.PlayerRepository;
import academy.repository.TeamRepository;
import academy.repository.TrainingRepository;
import jakarta.transaction.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private ActivityRepository activityRepository;


	@Override
	@Transactional
	public Team saveTeam(TeamRequestDto dto) {

	    Coach coach = null;
	    if (dto.getCoachId() != null) {
	        coach = coachRepository.findById(dto.getCoachId())
	                .orElseThrow(() -> new RuntimeException("Coach not found"));
	    }

	    Team team = new Team.Builder()
	            .setName(dto.getName())
	            .setAgeGroup(dto.getAgeGroup())
	            .setDivision(dto.getDivision())
	            .setCoach(coach)
	            .build();

	    if (coach != null) {
	        coach.addTeamInternal(team);
	    }

	    return teamRepository.save(team);
	}

	@Override
	public Optional<Team> findById(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public List<Team> findAllByCoachId(Long coachId) {
		return teamRepository.findAllByCoach_Id(coachId);
	}

	@Override
	public List<Team> findAllByAgeGroup(String ageGroup) {
		return teamRepository.findAllByAgeGroup(ageGroup);
	}

	@Override
	public Optional<TeamResponseDto> findTeamDtoById(Long id) {
		return teamRepository.findTeamDtoById(id);
	}

	@Override
	public List<TeamResponseDto> findAllTeamDto() {
		return teamRepository.findAllTeamDto();
	}

	@Override
	public List<TeamResponseDto> findAllTeamDtoByCoachId(Long coachId) {
		return teamRepository.findAllTeamDtoByCoachId(coachId);
	}

	@Override
	public List<TeamResponseDto> findAllTeamDtoWithPlayerCount() {
		return teamRepository.findAllTeamDtoWithPlayerCount();
	}

	@Override
	@Transactional
	public void deleteTeam(Long id) {
	    Team team = teamRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Team not found"));

	    // 1. Delete trainings for this team
	    trainingRepository.deleteAllByTeam_Id(id);

	    // 2. Delete activities for this team
	    activityRepository.deleteAllByTeam_Id(id);

	    // 3. Delete matches involving this team
	    Set<Match> matchesToDelete = new HashSet<>();
	    matchesToDelete.addAll(team.getHomeMatches());
	    matchesToDelete.addAll(team.getAwayMatches());
	    matchRepository.deleteAll(matchesToDelete);

	    // 4. Delete players on this team
	    Set<Player> playersToDelete = new HashSet<>(team.getPlayers());
	    playerRepository.deleteAll(playersToDelete);

	    // 5. Detach coach
	    if (team.getCoach() != null) {
	        team.getCoach().removeTeam(team);
	    }

	    // 6. Delete the team
	    teamRepository.delete(team);
	}
	
	@Override
	@Transactional
	public Team updateTeam(Long id, TeamRequestDto dto) {
	    Team team = teamRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Team not found"));
	    team.setName(dto.getName());
	    team.setAgeGroup(dto.getAgeGroup());
	    team.setDivision(dto.getDivision());
	    if (dto.getCoachId() != null) {
	        Coach coach = coachRepository.findById(dto.getCoachId())
	                .orElseThrow(() -> new RuntimeException("Coach not found"));
	        team.setCoach(coach);
	    }
	    return teamRepository.save(team);
	}

}
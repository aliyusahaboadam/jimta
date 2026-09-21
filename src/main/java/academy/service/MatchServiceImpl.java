package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.request.MatchRequestDto;
import academy.dto.response.MatchResponseDto;
import academy.interfaces.MatchService;
import academy.model.Match;
import academy.model.Team;
import academy.repository.MatchRepository;
import academy.repository.TeamRepository;
import jakarta.transaction.Transactional;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	
	@Override
	@Transactional
	public Match saveMatch(MatchRequestDto dto) {

	    Team homeTeam = teamRepository.findById(dto.getHomeTeamId())
	            .orElseThrow(() -> new RuntimeException("Home team not found"));
	    Team awayTeam = teamRepository.findById(dto.getAwayTeamId())
	            .orElseThrow(() -> new RuntimeException("Away team not found"));

	    Match match = new Match.Builder()
	            .setMatchDate(dto.getMatchDate())
	            .setVenue(dto.getVenue())
	            .setStatus(dto.getStatus())
	            .setHomeScore(dto.getHomeScore())
	            .setAwayScore(dto.getAwayScore())
	            .setHomeTeam(homeTeam)
	            .setAwayTeam(awayTeam)
	            .build();

	    homeTeam.addHomeMatchInternal(match);
	    awayTeam.addAwayMatchInternal(match);

	    return matchRepository.save(match);
	}

	@Override
	public Optional<Match> findById(Long id) {
		return matchRepository.findById(id);
	}

	@Override
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public List<Match> findAllByStatus(String status) {
		return matchRepository.findAllByStatus(status);
	}

	@Override
	public List<Match> findAllByTeamId(Long teamId) {
		return matchRepository.findAllByTeamId(teamId);
	}

	@Override
	public Optional<MatchResponseDto> findMatchDtoById(Long id) {
		return matchRepository.findMatchDtoById(id);
	}

	@Override
	public List<MatchResponseDto> findAllMatchDtoByTeamId(Long teamId) {
		return matchRepository.findAllMatchDtoByTeamId(teamId);
	}

	@Override
	public List<MatchResponseDto> findAllMatchDtoByStatus(String status) {
		return matchRepository.findAllMatchDtoByStatus(status);
	}
	
	@Override
	public void deleteMatch(Long id) {
	    matchRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public Match updateMatch(Long id, MatchRequestDto dto) {
	    Match match = matchRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Match not found"));
	    match.setMatchDate(dto.getMatchDate());
	    match.setVenue(dto.getVenue());
	    match.setStatus(dto.getStatus());
	    match.setHomeScore(dto.getHomeScore());
	    match.setAwayScore(dto.getAwayScore());
	    return matchRepository.save(match);
	}
	
	@Override
	public List<MatchResponseDto> findAllMatchDto() {
	    return matchRepository.findAllMatchDto();
	}

	
}

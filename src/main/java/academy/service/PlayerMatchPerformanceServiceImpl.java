package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.dto.request.PlayerMatchPerformanceRequestDto;
import academy.dto.response.PlayerMatchPerformanceResponseDto;
import academy.interfaces.PlayerMatchPerformanceService;
import academy.model.Match;
import academy.model.Player;
import academy.model.PlayerMatchPerformance;
import academy.repository.MatchRepository;
import academy.repository.PlayerMatchPerformanceRepository;
import academy.repository.PlayerRepository;
import jakarta.transaction.Transactional;

@Service
public class PlayerMatchPerformanceServiceImpl implements PlayerMatchPerformanceService {

	@Autowired
	private PlayerMatchPerformanceRepository playerMatchPerformanceRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MatchRepository matchRepository;

	@Override
	@Transactional
	public PlayerMatchPerformance savePerformance(PlayerMatchPerformanceRequestDto dto) {

	    Player player = playerRepository.findById(dto.getPlayerId())
	            .orElseThrow(() -> new RuntimeException("Player not found"));
	    Match match = matchRepository.findById(dto.getMatchId())
	            .orElseThrow(() -> new RuntimeException("Match not found"));

	    PlayerMatchPerformance perf = new PlayerMatchPerformance.Builder()
	            .setGoals(dto.getGoals())
	            .setAssists(dto.getAssists())
	            .setYellowCards(dto.getYellowCards())
	            .setRedCards(dto.getRedCards())
	            .setMinutesPlayed(dto.getMinutesPlayed())
	            .setStarted(dto.getStarted())
	            .setPlayer(player)
	            .setMatch(match)
	            .build();

	    player.addPerformanceInternal(perf);
	    match.addPerformanceInternal(perf);

	    return playerMatchPerformanceRepository.save(perf);
	}
	

	@Override
	public Optional<PlayerMatchPerformance> findById(Long id) {
		return playerMatchPerformanceRepository.findById(id);
	}

	@Override
	public List<PlayerMatchPerformance> findAllByMatchId(Long matchId) {
		return playerMatchPerformanceRepository.findAllByMatch_Id(matchId);
	}

	@Override
	public List<PlayerMatchPerformance> findAllByPlayerId(Long playerId) {
		return playerMatchPerformanceRepository.findAllByPlayer_Id(playerId);
	}

	@Override
	public Optional<PlayerMatchPerformance> findByPlayerIdAndMatchId(Long playerId, Long matchId) {
		return playerMatchPerformanceRepository.findByPlayer_IdAndMatch_Id(playerId, matchId);
	}

	@Override
	public List<PlayerMatchPerformanceResponseDto> findAllPerformanceDtoByMatchId(Long matchId) {
		return playerMatchPerformanceRepository.findAllPerformanceDtoByMatchId(matchId);
	}

	@Override
	public PlayerMatchPerformanceResponseDto findSeasonTotalsDtoByPlayerId(Long playerId) {
		return playerMatchPerformanceRepository.findSeasonTotalsDtoByPlayerId(playerId);
	}

}

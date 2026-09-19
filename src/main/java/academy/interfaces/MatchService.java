package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.MatchRequestDto;
import academy.dto.response.MatchResponseDto;
import academy.model.Match;

public interface MatchService {

	Match saveMatch(MatchRequestDto dto);

	Optional<Match> findById(Long id);

	List<Match> getAllMatches();

	List<Match> findAllByStatus(String status);

	List<Match> findAllByTeamId(Long teamId);

	Optional<MatchResponseDto> findMatchDtoById(Long id);

	List<MatchResponseDto> findAllMatchDtoByTeamId(Long teamId);

	List<MatchResponseDto> findAllMatchDtoByStatus(String status);
	
	void deleteMatch(Long id);
	
	Match updateMatch(Long id, MatchRequestDto dto);

}

package academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import academy.dto.request.PlayerRequestDto;
import academy.dto.response.PlayerResponseDto;
import academy.exception.AcademyException;
import academy.interfaces.PlayerService;
import academy.model.Player;
import academy.model.Profile;
import academy.model.Team;
import academy.model.User;
import academy.repository.PlayerRepository;
import academy.repository.TeamRepository;
import academy.repository.UserRepository;
import academy.utility.IdentityGenerator;
import jakarta.transaction.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
    private TeamRepository teamRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    
  
    
    @Override
    @Transactional
    public Player savePlayer(PlayerRequestDto dto) {
    	
    	if (userRepository.existsByEmail(dto.getEmail())) {
	        throw new AcademyException(
	            "A user with this email already exists",
	            HttpStatus.CONFLICT
	        );
	    }

        // 1. Resolve team (optional)
        Team team = null;
        if (dto.getTeamId() != null) {
            team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found: " + dto.getTeamId()));
        }

        // 2. Generate unique username
        String username;
        do {
            username = IdentityGenerator.generatePlayerUsername();
        } while (userRepository.findByUsername(username) != null);

        // 3. Build User (from request DTO fields, or defaults)
        User user = new User.Builder()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(username)) // default = username
                .setEmail(dto.getEmail())
                .setRole("PLAYER")
                .build();

        // 4. Build Player
        Player player = new Player.Builder()
                .setPosition(dto.getPosition())
                .setJerseyNumber(dto.getJerseyNumber())
                .setPlayerNumber(dto.getPlayerNumber())
                .setNationality(dto.getNationality())
                .setPreferredFoot(dto.getPreferredFoot())
                .setHeightCm(dto.getHeightCm())
                .setWeightKg(dto.getWeightKg())
                .setPreviousClub(dto.getPreviousClub())
                .setPhotoUrl(dto.getPhotoUrl())
                .setTeam(team)
                .setUser(user)
                .build();

        // 5. Back-reference: user -> player
        user.setPlayerInternal(player);

        // 6. If team exists, hook into the bidirectional collection
        if (team != null) {
            team.addPlayerInternal(player);
        }
        
        
     
        Profile p = dto.getProfile();

        Profile profile = new Profile.Builder()
                    .setFirstname(p.getFirstname())
                    .setSurname(p.getSurname())
                    .setLastname(p.getLastname())
                    .setDateOfBirth(p.getDateOfBirth())
                    .setGender(p.getGender())
                    .setPhoneNumber(p.getPhoneNumber())
                    .setPlayer(player)   // ← owner side
                    .build();
        

        // Attach profile to player
        player.setProfile(profile);   

        return playerRepository.save(player);
    }

	

	@Override
	public Player savePlayer(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Optional<Player> findById(Long id) {
		return playerRepository.findById(id);
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public List<Player> findAllByTeamId(Long teamId) {
		return playerRepository.findAllByTeam_Id(teamId);
	}

	@Override
	public Optional<Player> findByJerseyNumberAndTeamId(Integer jerseyNumber, Long teamId) {
		return playerRepository.findByJerseyNumberAndTeam_Id(jerseyNumber, teamId);
	}

	@Override
	public Optional<PlayerResponseDto> findPlayerDtoById(Long id) {
		return playerRepository.findPlayerDtoById(id);
	}

	@Override
	public List<PlayerResponseDto> findAllPlayerDtoByTeamId(Long teamId) {
		return playerRepository.findAllPlayerDtoByTeamId(teamId);
	}

	@Override
	public Optional<PlayerResponseDto> findPlayerProfileDtoById(Long id) {
		return playerRepository.findPlayerProfileDtoById(id);
	}

	@Override
	@Transactional
	public void deletePlayer(Long id) {
	    Player player = playerRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Player not found"));

	    // Detach from team so we don't drag the team/coach into the delete
	    if (player.getTeam() != null) {
	        Team team = player.getTeam();
	        team.removePlayer(player);
	    }

	    // Cascades on user, profile, performances will handle the rest
	    playerRepository.delete(player);
	}
	
	@Override
	public List<PlayerResponseDto> findAllPlayerDto() {
	    return playerRepository.findAllPlayerDto();
	}

}

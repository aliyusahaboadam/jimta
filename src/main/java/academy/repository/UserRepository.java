package academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.UserResponseDto;
import academy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	  
	
	  User findByUsername(String username);
    
	  User findByEmail(String email);
	  
	  User findByRole(String role);
	  
	  boolean existsByEmail(String email);
	  boolean existsByUsername(String username);


	@Query("SELECT new academy.dto.response.UserResponseDto(u.id, u.username, u.email, u.role) "
			+ "FROM User u WHERE u.id = :id")
	Optional<UserResponseDto> findUserDtoById(@Param("id") Long id);
	
	boolean existsByRole(String role);

}

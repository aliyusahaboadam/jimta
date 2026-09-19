package academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import academy.dto.response.AdminResponseDto;
import academy.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);

	boolean existsByEmail(String email);

	@EntityGraph(attributePaths = {}) // don't pull in user/forgotPasswordTokens for a plain list
	List<Admin> findAll();

	@Query("SELECT new academy.dto.response.AdminResponseDto(a.id, a.firstname, a.surname, a.lastname, a.email, a.phoneNumber) "
			+ "FROM Admin a WHERE a.id = :id")
	Optional<AdminResponseDto> findAdminDtoById(@Param("id") Long id);

	@Query("SELECT academy.dto.response.AdminResponseDto(a.id, a.firstname, a.surname, a.lastname, a.email, a.phoneNumber) "
			+ "FROM Admin a")
	List<AdminResponseDto> findAllAdminDto();


}

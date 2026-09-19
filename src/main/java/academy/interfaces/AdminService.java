package academy.interfaces;

import java.util.List;
import java.util.Optional;

import academy.dto.request.AdminRequestDto;
import academy.dto.response.AdminResponseDto;
import academy.model.Admin;


public interface AdminService {

	Admin saveAdmin(AdminRequestDto dto);

	Optional<Admin> findById(Long id);

	Optional<Admin> findByEmail(String email);

	boolean existsByEmail(String email);

	List<Admin> getAllAdmins();

	Optional<AdminResponseDto> findAdminDtoById(Long id);

	List<AdminResponseDto> findAllAdminDto();

	void deleteAdmin(Long id);
	Admin updateAdmin(Long id, AdminRequestDto dto);

}

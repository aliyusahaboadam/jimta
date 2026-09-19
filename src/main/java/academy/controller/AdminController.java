package academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.auth.AuthUserService;
import academy.dto.request.AdminRequestDto;
import academy.dto.response.AdminResponseDto;
import academy.exception.ResourceNotFoundException;
import academy.interfaces.AdminService;
import academy.payload.BodyMessage;

@RequestMapping("/v1/api/admin")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AuthUserService authUserService;


	@PostMapping("/add")
	public ResponseEntity<BodyMessage> addAdmin(@RequestBody AdminRequestDto dto) {
		adminService.saveAdmin(dto);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Admin added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(bodyMessage);
	}


	@GetMapping("/get-by-id/{id}")
	public AdminResponseDto getAdminById(@PathVariable Long id) {
		return adminService.findAdminDtoById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
	}


	@GetMapping("/get-authenticated-admin")
	public AdminResponseDto getAuthenticatedAdmin() {
		return adminService.findAdminDtoById(authUserService.authenticatedAdminId())
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
	}


	@GetMapping("/get-all")
	public List<AdminResponseDto> getAllAdmins() {
		return adminService.findAllAdminDto();
	}


	@GetMapping("/exists-by-email/{email}")
	public boolean existsByEmail(@PathVariable String email) {
		return adminService.existsByEmail(email);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BodyMessage> deleteAdminById(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setId(id);
		bodyMessage.setMessage("Admin deleted successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}


	@GetMapping("/welcome")
	public String welcome() {
		return authUserService.welcomeAdmin();
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BodyMessage> updateAdmin(@PathVariable Long id,
	        @RequestBody AdminRequestDto dto) {
	    adminService.updateAdmin(id, dto);
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setId(id);
	    bodyMessage.setMessage("Admin updated successfully");
	    return ResponseEntity.ok(bodyMessage);
	}

}

package academy.controller;

import org.aspectj.bridge.AbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import academy.auth.AuthUserService;
import academy.model.Player;
import academy.payload.BodyMessage;
import academy.interfaces.PlayerService;
import academy.utility.S3Util;

@RequestMapping("/v1/api/player")
@RestController
public class PlayerPhotoController {

	@Autowired
	private AuthUserService authUserService;

	@Autowired
	private PlayerService playerService;

	private static final long MAX_SIZE = 1_048_576L;


	/**
	 * Admin flow — uploads the file and returns the generated filename.
	 * The caller then includes that filename in the Add/Update Player payload
	 * as photoUrl.
	 */
	@PostMapping("/upload-photo")
	public ResponseEntity<BodyMessage> uploadPhoto(
			@RequestParam("image") MultipartFile multipartFile) {

		try {
			if (multipartFile == null || multipartFile.isEmpty()) {
				return ResponseEntity.badRequest()
						.body(new BodyMessage("No file provided"));
			}
			if (multipartFile.getSize() > MAX_SIZE) {
				return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
						.body(new BodyMessage("Photo must be 1 MB or smaller"));
			}
			if (!isValidImageType(multipartFile.getContentType())) {
				return ResponseEntity.badRequest()
						.body(new BodyMessage("Invalid file type. Only images are allowed."));
			}

			String originalFileName = StringUtils.cleanPath(
					multipartFile.getOriginalFilename() == null
							? "player-photo"
							: multipartFile.getOriginalFilename());
			String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

			S3Util.uploadFile(uniqueFileName, multipartFile.getInputStream());

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new BodyMessage(uniqueFileName));

		} catch (AbortException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new BodyMessage("Failed to upload file to storage"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new BodyMessage("Failed to upload photo: " + e.getMessage()));
		}
	}


	/**
	 * Self-service flow — a logged-in player changes their own photo.
	 */
	@PostMapping("/save-photo")
	public ResponseEntity<BodyMessage> savePhoto(
			@RequestParam("image") MultipartFile multipartFile) {

		try {
			if (multipartFile == null || multipartFile.isEmpty()) {
				return ResponseEntity.badRequest()
						.body(new BodyMessage("No file provided"));
			}
			if (multipartFile.getSize() > MAX_SIZE) {
				return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
						.body(new BodyMessage("Photo must be 1 MB or smaller"));
			}
			if (!isValidImageType(multipartFile.getContentType())) {
				return ResponseEntity.badRequest()
						.body(new BodyMessage("Invalid file type. Only images are allowed."));
			}

			Player player = playerService
					.findById(authUserService.authenticatedPlayerId())
					.orElseThrow(() -> new RuntimeException("Player not found"));

			String originalFileName = StringUtils.cleanPath(
					multipartFile.getOriginalFilename() == null
							? "player-photo"
							: multipartFile.getOriginalFilename());
			String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

			// Upload first, then persist — DB never references a missing file
			S3Util.uploadFile(uniqueFileName, multipartFile.getInputStream());

			player.setPhotoUrl(uniqueFileName);
			playerService.savePlayer(player);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new BodyMessage(uniqueFileName));

		} catch (AbortException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new BodyMessage("Failed to upload file to storage"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new BodyMessage("Failed to save photo: " + e.getMessage()));
		}
	}


	private boolean isValidImageType(String contentType) {
		return contentType != null && (
				contentType.equals("image/jpeg") ||
				contentType.equals("image/jpg")  ||
				contentType.equals("image/png")  ||
				contentType.equals("image/gif")  ||
				contentType.equals("image/webp")
		);
	}

}

package co.simplon.p_business.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p_business.dos.Login;
import co.simplon.p_business.services.BankService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {

	private final BankService service;

	public BankController(BankService service) {
		this.service = service;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	void login(@RequestBody @Valid Login inputs, HttpServletResponse response) {
		service.login(inputs, response);
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestParam String toAccountNumber, @RequestParam int amount,
			@CookieValue(value = "SESSION_ID", defaultValue = "") String sessionId) {

		if (sessionId == null) {
			return ResponseEntity.status(401).body("Not autorised login");
		}

		if (service.checkSessionId(sessionId)) {
			service.transfer(toAccountNumber, amount, sessionId);
			return ResponseEntity.ok("Transfer completed");
		} else {
			return ResponseEntity.status(401).body("Session not valid");
		}
	}

}
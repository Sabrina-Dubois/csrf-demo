package co.simplon.p_business.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p_business.dos.Login;
import co.simplon.p_business.services.BankService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {

	private final BankService service;

	public BankController(BankService service) {
		this.service = service;
	}

	public static class TransferRequest {
		private String toAccountNumber;
		private int amount;

		// Getters
		public String getToAccountNumber() {
			return toAccountNumber;
		}

		public int getAmount() {
			return amount;
		}

		// Setters
		public void setToAccountNumber(String toAccountNumber) {
			this.toAccountNumber = toAccountNumber;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	void login(@RequestBody @Valid Login inputs, HttpServletResponse response) {
		service.login(inputs, response);
	}

	@GetMapping("/ping")
	@ResponseStatus(HttpStatus.OK)
	public Boolean ping() {
		return true;
	}

	@GetMapping("/csrf-token")
	public Map<String, String> csrfToken(HttpServletRequest request) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return Map.of("token", csrfToken.getToken());
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody TransferRequest request,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId, HttpServletRequest servletRequest) {

		{

			if (sessionId == null || sessionId.isEmpty()) {
				return ResponseEntity.status(401).body("Not autorised login");
			}

			if (service.checkSessionId(sessionId)) {
				service.transfer(request.getToAccountNumber(), request.getAmount(), sessionId);
				return ResponseEntity.ok("Transfer completed");
			} else {
				return ResponseEntity.status(401).body("Session not valid");
			}
		}
	}

}
package co.simplon.p_business.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.simplon.p_business.dos.Login;
import co.simplon.p_business.entities.Account;
import co.simplon.p_business.entities.User;
import co.simplon.p_business.entities.UserSession;
import co.simplon.p_business.repositories.AccountRepository;
import co.simplon.p_business.repositories.UserRepository;
import co.simplon.p_business.repositories.UserSessionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class BankService {

	private final AccountRepository accountsRepo;
	private final UserRepository usersRepo;
	private final UserSessionRepository userSessionsRepo;

	public BankService(AccountRepository accountsRepo, UserRepository UsersRepo,
			UserSessionRepository userSessionsRepo) {
		this.accountsRepo = accountsRepo;
		this.usersRepo = UsersRepo;
		this.userSessionsRepo = userSessionsRepo;
	}

	public void login(@Valid Login inputs, HttpServletResponse response) {
		User user = usersRepo.findByUsername(inputs.username());
		if (user != null && user.getPassword().equals(inputs.password())) {
			String sessionID = UUID.randomUUID().toString();
			Cookie cookie = new Cookie("JSESSIONID", sessionID);
			// cookie.setAttribute("SameSite", "None");
			// cookie.setSecure(true);// added on 13th may
			response.addCookie(cookie);

			LocalDateTime dateTime = LocalDateTime.now();
			UserSession userSession = new UserSession();
			userSession.setSessionId(sessionID);
			userSession.setUser(user);
			userSession.setCreatedAt(dateTime);
			userSessionsRepo.save(userSession);

		}
	}

	public boolean checkSessionId(String sessionId) {
		return userSessionsRepo.existsBySessionId(sessionId);
	}

	@Transactional
	public void transfer(String toAccountNumber, int amount, String sessionId) {

		Account toAccount = accountsRepo.findByAccountNumber(toAccountNumber);
		UserSession fromUserSession = userSessionsRepo.findBySessionId(sessionId);
		Account fromAccount = accountsRepo.findByUser(fromUserSession.getUser());

		toAccount.setBalance(toAccount.getBalance() + amount);
		fromAccount.setBalance(fromAccount.getBalance() - amount);

		accountsRepo.save(toAccount);
		accountsRepo.save(fromAccount);

	}
}
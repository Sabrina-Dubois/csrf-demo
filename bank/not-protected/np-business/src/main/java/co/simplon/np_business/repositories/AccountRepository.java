package co.simplon.np_business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.np_business.entities.Account;
import co.simplon.np_business.entities.User;
import jakarta.validation.constraints.NotBlank;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumber(@NotBlank String s);

	Account findByUser(User user);
}
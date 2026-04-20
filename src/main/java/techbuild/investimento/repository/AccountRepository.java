package techbuild.investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techbuild.investimento.entity.Account;
import techbuild.investimento.entity.Stock;
import techbuild.investimento.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}

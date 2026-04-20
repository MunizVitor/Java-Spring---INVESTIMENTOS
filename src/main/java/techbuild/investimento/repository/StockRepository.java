package techbuild.investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techbuild.investimento.entity.Account;
import techbuild.investimento.entity.Stock;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}

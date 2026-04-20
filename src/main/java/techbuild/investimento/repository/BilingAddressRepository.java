package techbuild.investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techbuild.investimento.entity.BillingAddress;

import java.util.UUID;

@Repository
public interface BilingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}

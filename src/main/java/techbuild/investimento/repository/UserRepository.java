package techbuild.investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techbuild.investimento.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByIsAtivo(boolean isAtivo);

    Optional<User> findByUserIdAndIsAtivoTrue(UUID userId);

}

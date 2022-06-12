package kz.ecommerce.repository;

import kz.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByIdAsc();

    Optional<User> findByEmail(String email);

    Optional<User> findByActivationCode(String code);

    @Query("SELECT user.email FROM User user WHERE user.passwordResetCode = :code")
    Optional<String> getEmailByPasswordResetCode(String code);
}

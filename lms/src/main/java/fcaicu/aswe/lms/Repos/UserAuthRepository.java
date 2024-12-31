package fcaicu.aswe.lms.Repos;


import fcaicu.aswe.lms.Models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByEmail(String email);
    Boolean existsByEmail(String email);
}

package fcaicu.aswe.lms.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fcaicu.aswe.lms.Models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}

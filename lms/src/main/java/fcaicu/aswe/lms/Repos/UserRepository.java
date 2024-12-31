package fcaicu.aswe.lms.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fcaicu.aswe.lms.Models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM UserTable WHERE Name = :name", nativeQuery = true)
    User findByName(String name);
}
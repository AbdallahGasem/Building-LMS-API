package fcaicu.aswe.lms.Repos;

import fcaicu.aswe.lms.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM Notification WHERE UID = :userId AND Status = 0 ", nativeQuery = true)
    List<Notification> findByUserIdAndReadFalse(int userId);

    @Query(value = "SELECT * FROM Notification WHERE UID = :userId", nativeQuery = true)
    List<Notification> findByUserId(int userId);
}
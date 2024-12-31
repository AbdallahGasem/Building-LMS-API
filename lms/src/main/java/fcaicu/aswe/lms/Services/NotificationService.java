package fcaicu.aswe.lms.Services;

import fcaicu.aswe.lms.Models.Notification;
import fcaicu.aswe.lms.Repos.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public String sendNotification(String message, int userId) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);
        notification.setRead(false);
        notificationRepository.save(notification);
        return "Notification sent";
    }

    public List<Notification> fetchNotifications(int userId, boolean unreadOnly) {
        if (unreadOnly) {
            return notificationRepository.findByUserIdAndReadFalse(userId);
        } else {
            return notificationRepository.findByUserId(userId);
        }
    }
}
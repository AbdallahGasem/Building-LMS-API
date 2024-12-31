package fcaicu.aswe.lms.Controllers;

import fcaicu.aswe.lms.Models.Notification;
import fcaicu.aswe.lms.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send/{userId}")
    public String sendNotification(@RequestBody String message, @PathVariable int userId) {
        return notificationService.sendNotification(message, userId);
    }

    @GetMapping("/{userId}")
    public List<Notification> fetchNotifications(@PathVariable int userId, @RequestParam boolean unreadOnly) {
        return notificationService.fetchNotifications(userId, unreadOnly);
    }
}
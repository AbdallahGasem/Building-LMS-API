package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NID", insertable=false, updatable=false)
    private int id;
    
    @Column(name = "NID", nullable = false)
    private String message;

    @Column(name = "UID",nullable = false)
    private int userId;

    @Column(name = "Status", nullable = false)
    private boolean isRead;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
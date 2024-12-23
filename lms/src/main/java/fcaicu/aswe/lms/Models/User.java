package fcaicu.aswe.lms.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.awt.Image;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    private String Name; // VARCHAR(30),
    private String Email; // unique not null
    private String Password; // VARCHAR(8) NOT NULL,
    private String Role; // CHECK ([Role] IN ('ADMIN', 'INSTRUC', 'STUD')),
    private Image ProfilePic; 
    private String CreationAdminID; // fk to UID need to map this relation ya wagdy

    // what for?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<User> users;
    
    // submission relation
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Submission> submissions;




    public int getUID() {
        return UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Image getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(Image profilePic) {
        ProfilePic = profilePic;
    }

    public String getCreationAdminID() {
        return CreationAdminID;
    }

    public void setCreationAdminID(String creationAdminID) {
        CreationAdminID = creationAdminID;
    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }

}

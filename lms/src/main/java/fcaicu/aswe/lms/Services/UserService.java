package fcaicu.aswe.lms.Services;

import fcaicu.aswe.lms.Repos.UserRepository;
import fcaicu.aswe.lms.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
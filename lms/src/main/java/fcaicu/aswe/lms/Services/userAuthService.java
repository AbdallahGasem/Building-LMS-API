package fcaicu.aswe.lms.Services;

import fcaicu.aswe.lms.Models.UserAuth;
import fcaicu.aswe.lms.Repos.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userAuthService {
    @Autowired
    UserAuthRepository userRepo;
    // Get user by id
    public UserAuth getUserbyid(Integer uid)
    {
        return userRepo.findById(uid).orElseThrow(() ->
                new RuntimeException("user doesn't exist")
        );
    }


}

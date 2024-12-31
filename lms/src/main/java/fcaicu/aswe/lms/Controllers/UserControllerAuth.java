package fcaicu.aswe.lms.Controllers;

import fcaicu.aswe.lms.Models.Role;
import fcaicu.aswe.lms.Models.UserAuth;
import fcaicu.aswe.lms.Repos.UserAuthRepository;
import fcaicu.aswe.lms.Services.JwtService;
import fcaicu.aswe.lms.Services.userAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerAuth {
    @Autowired
    private final userAuthService  uservice;
    private final UserAuthRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuth user) {
        // Check if the email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Validate the CreationAdminId, if provided
        if (user.getCreationAdminId() != null) {
            UserAuth adminUser = userRepository.findById(user.getCreationAdminId()).orElse(null);
            if (adminUser == null || adminUser.getRole() != Role.ADMIN) {
                return new ResponseEntity<>("Invalid CreationAdminID or the user is not an ADMIN", HttpStatus.FORBIDDEN);
            }

        }

        // Create a new user and save it
        UserAuth newUser = new UserAuth();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        newUser.setCreationAdminId(user.getCreationAdminId());

        user = userRepository.save(newUser);

        // Generate a JWT token for the user
        String token = jwtService.generateToken(user);

        // Create a response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuth user) {
        // Find user by email
        UserAuth foundUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (foundUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Validate the password
        if (!bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Generate a JWT token for the user
        String token = jwtService.generateToken(foundUser);

        // Create a response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", foundUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // GET endpoint to fetch user by ID
    @GetMapping("/{uid}")
    public ResponseEntity<UserAuth> getUserById(@PathVariable Integer uid) {
        UserAuth user = uservice.getUserbyid(uid);
        return ResponseEntity.ok(user);
    }
}

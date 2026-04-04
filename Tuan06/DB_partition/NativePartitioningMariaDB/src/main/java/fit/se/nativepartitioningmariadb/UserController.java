package fit.se.nativepartitioningmariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // API Test thêm User: POST http://localhost:8080/api/users?username=NguyenVanA&gender=NAM
    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String username, @RequestParam String gender) {
        User savedUser = userService.createUser(username, gender);
        return ResponseEntity.ok(savedUser);
    }

    // API Lấy danh sách User: GET http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
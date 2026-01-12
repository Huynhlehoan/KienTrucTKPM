package fit.se.huynhlehoan_22682941_tuan01.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    // Đăng ký: Lưu user + pass vào Redis
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        redisTemplate.opsForValue().set("user:" + username, password);
        return "Dang ky thanh cong: " + username;
    }

    // Đăng nhập: Kiểm tra Redis -> Trả về Token
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String storedPass = redisTemplate.opsForValue().get("user:" + username);
        if (storedPass != null && storedPass.equals(password)) {
            return jwtUtil.generateToken(username); // Trả về JWT
        }
        return "Sai mat khau hoac tai khoan!";
    }
}
package fit.se.nativepartitioningmariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String gender) {
        // Chuẩn hóa dữ liệu đầu vào
        String upperGender = gender.toUpperCase();
        if (!upperGender.equals("NAM") && !upperGender.equals("NU")) {
            throw new IllegalArgumentException("Gender chỉ được là NAM hoặc NU");
        }

        User user = new User();
        user.setUsername(username);
        user.setGender(upperGender);

        // Khi gọi save(), MariaDB sẽ tự động kiểm tra cột gender
        // và lưu bản ghi vào table_user_01 (nếu là NAM) hoặc table_user_02 (nếu là NU)
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

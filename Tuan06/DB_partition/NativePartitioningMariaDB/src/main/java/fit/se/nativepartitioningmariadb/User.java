package fit.se.nativepartitioningmariadb;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chỉ duy nhất id làm khóa chính trong mắt JPA

    @Column(name = "gender", length = 10)
    private String gender; // Mất @Id, giờ nó chỉ là cột bình thường

    @Column(name = "username", length = 100)
    private String username;
}
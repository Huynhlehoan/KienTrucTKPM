package fit.se.cms_layredarchitechture.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "themes")
public class Theme {
    @Id
    private Long id = 1L; // Chỉ lưu 1 record duy nhất cho hệ thống
    private String mode; // "light" hoặc "dark"

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
}
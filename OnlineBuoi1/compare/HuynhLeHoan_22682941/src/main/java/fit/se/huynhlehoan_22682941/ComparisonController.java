package fit.se.huynhlehoan_22682941;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ComparisonController {

    // REST: Gọi URL là trả về cục JSON cố định
    @GetMapping("/rest/student/{id}")
    public Map<String, Object> getStudentRest(@PathVariable String id) {
        return Map.of(
                "id", id,
                "name", "Nguyen Van A",
                "age", 22,
                "email", "vana@gmail.com"
        );
    }
}
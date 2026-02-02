package fit.se.huynhlehoan_22682941;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class GraphqlController {

    @QueryMapping
    public Map<String, Object> student(@Argument String id) {
        // Chỉ trả về data, GraphQL tự lọc field theo yêu cầu client
        return Map.of(
                "id", id,
                "name", "Le Thi B", // GraphQL Demo
                "age", 21,
                "email", "thib@gmail.com"
        );
    }
}
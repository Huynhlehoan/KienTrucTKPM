package iuh.fit.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final ExternalService externalService;

    public DemoController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/test")
    public String test() {
        return externalService.callNodeJs();
    }
}

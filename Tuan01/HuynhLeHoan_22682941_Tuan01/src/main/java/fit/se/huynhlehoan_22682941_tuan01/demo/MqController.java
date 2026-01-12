package fit.se.huynhlehoan_22682941_tuan01.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String QUEUE_NAME = "bai_tap_mq";

    // 1. PUSH message into MQ
    @GetMapping("/push")
    public String push(@RequestParam String msg) {
        redisTemplate.opsForList().leftPush(QUEUE_NAME, msg);
        return "Đã đẩy vào hàng đợi: " + msg;
    }

    // 2. READ message from MQ
    @GetMapping("/read")
    public String read() {
        String msg = redisTemplate.opsForList().rightPop(QUEUE_NAME);
        return (msg == null) ? "Hết tin nhắn!" : "Đọc được: " + msg;
    }
}
package iuh.fit.demo;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    private final RestTemplate restTemplate = new RestTemplate();

    // Bulkhead -> RateLimiter -> CircuitBreaker -> Retry
    @Bulkhead(name = "backendB", fallbackMethod = "bulkheadFallback")
    @RateLimiter(name = "backendB", fallbackMethod = "rateLimitFallback")
    @CircuitBreaker(name = "backendB", fallbackMethod = "fallback")
    @Retry(name = "backendB", fallbackMethod = "retryFallback") // Có fallback riêng cho retry nếu muốn
    public String callNodeJs() {
        System.out.println("--> Spring Boot: Gọi API (Sync)...");
        return restTemplate.getForObject("http://localhost:8081/api/data", String.class);
    }

    // --- CÁC HÀM FALLBACK ---

    // 1. Fallback cho Bulkhead (Quan trọng nhất lúc này)
    public String bulkheadFallback(BulkheadFullException ex) {
        return "FALLBACK BULKHEAD: Server quá tải (Hết slot)!";
    }

    // 2. Fallback cho Rate Limiter
    public String rateLimitFallback(io.github.resilience4j.ratelimiter.RequestNotPermitted e) {
        return "FALLBACK RATE LIMIT: Gửi chậm thôi!";
    }

    // 3. Fallback chung cho Retry và Circuit Breaker (Khi server chết hẳn)
    public String fallback(Exception e) {
        return "FALLBACK CHUNG: Service B sập hoặc CB mở. (" + e.getMessage() + ")";
    }

    // 4. Fallback riêng cho Retry
    public String retryFallback(Exception e) {
        return "FALLBACK RETRY: Đã thử lại 3 lần mà không được.";
    }
}
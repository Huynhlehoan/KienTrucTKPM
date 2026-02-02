package iuh.fit.demo;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.common.bulkhead.configuration.BulkheadConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public BulkheadConfigCustomizer backendBCustomizer() {
        return BulkheadConfigCustomizer.of("backendB", builder -> builder
                .maxConcurrentCalls(1)
                .maxWaitDuration(Duration.ZERO));
    }
}
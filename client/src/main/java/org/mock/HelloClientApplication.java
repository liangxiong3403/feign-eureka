package org.mock;

import lombok.extern.slf4j.Slf4j;
import org.example.HelloClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Spencer Gibb
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.example", "org.mock"})
@RestController
@EnableFeignClients(basePackageClasses = HelloClient.class)
public class HelloClientApplication {

    @Autowired
    private ObjectProvider<HelloClient> provider;

    @RequestMapping("/")
    public String hello(@RequestParam String username) {
        if (log.isInfoEnabled()) {
            log.info("client receive request...");
        }
        AtomicReference<String> result = new AtomicReference<>("default");
        provider.ifAvailable(client -> {
            result.set(client.hello(username));
        });
        return result.get();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }
}

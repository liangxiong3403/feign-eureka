package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-08-13 13:06
 * @description hello api
 **/
@FeignClient(name = "HelloServer", fallback = HelloClientFallback.class)
@RequestMapping("/server")
public interface HelloClient {

    /**
     * @return 问候
     * @author liangxiong
     */
    @GetMapping("/hello")
    String hello(@RequestParam("username") String username);

}

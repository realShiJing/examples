package com.nchu.apollo.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description   --spring.profiles.active=prod
 *                --spring.profiles.active=dev
 * @Author yangsj
 * @Date 2020/12/23 下午3:56
 **/
@SpringBootApplication
public class ProfilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfilesApplication.class, args);
    }

}

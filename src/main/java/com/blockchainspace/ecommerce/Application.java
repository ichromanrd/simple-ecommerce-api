package com.blockchainspace.ecommerce;

import com.blockchainspace.ecommerce.config.BcryptPasswordService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blockchainspace.ecommerce.persistence.mapper")
public class Application implements CommandLineRunner {

    @Autowired
    private BcryptPasswordService bcryptPasswordService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("admin = " + bcryptPasswordService.encryptPassword("admin"));
//        System.out.println("seller1 = " + bcryptPasswordService.encryptPassword("seller1"));
//        System.out.println("seller2 = " + bcryptPasswordService.encryptPassword("seller2"));
//        System.out.println("buyer1 = " + bcryptPasswordService.encryptPassword("buyer1"));
//        System.out.println("buyer2 = " + bcryptPasswordService.encryptPassword("buyer2"));
    }
}

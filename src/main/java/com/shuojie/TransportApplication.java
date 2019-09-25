package com.shuojie;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.MultipartConfigElement;

//@EnableJpaAuditing
//@EnableScheduling
@SpringBootApplication
@MapperScan("com.shuojie.dao")
@EnableAsync
public class TransportApplication   {
    public static void main(String[] args) {
        SpringApplication.run(TransportApplication.class,args);

    }



  /*  @Override
    public void run (String ...args)throws Exception{
        new MyServer();
    // new Mqttclien();implements CommandLineRunner
}*/
}

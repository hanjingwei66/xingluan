package com.shuojie;

import com.shuojie.nettyService.MyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class TransportApplication implements CommandLineRunner  {
    public static void main(String[] args) {
        SpringApplication.run(TransportApplication.class,args);
    }

    @Override
    public void run (String ...args)throws Exception{
        new MyServer();
    }
}

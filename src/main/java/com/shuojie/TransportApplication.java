package com.shuojie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing
//@EnableScheduling
@SpringBootApplication
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

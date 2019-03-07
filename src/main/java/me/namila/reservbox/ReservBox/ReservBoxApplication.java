package me.namila.reservbox.ReservBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReservBoxApplication
{

    public static void main(String[] args) {
        SpringApplication.run(ReservBoxApplication.class, args);
    }

}

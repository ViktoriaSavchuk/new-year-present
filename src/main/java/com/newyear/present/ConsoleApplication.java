package com.newyear.present;

import com.newyear.present.ui.ConsoleUI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Log4j2
@SpringBootApplication
@EnableJpaRepositories
public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    ConsoleUI consoleUI;

    public static void main(String[] args) {

        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        consoleUI.run();
    }
}

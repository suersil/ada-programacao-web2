package tech.ada.java.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }
    @Bean
    // Criamos o Bean de configuracao do modelMapper, isso significa que ensinamos o spring como criar o ModelMapper
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

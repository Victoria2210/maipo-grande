package com.duoc.maipogrande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MaipograndeApplication implements CommandLineRunner {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MaipograndeApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        String[] contraseñas = new String[6];
        contraseñas[0] = passwordEncoder.encode("cliente");
        contraseñas[1] = passwordEncoder.encode("hugo");
        contraseñas[2] = passwordEncoder.encode("panchito");
        contraseñas[3] = passwordEncoder.encode("123");
        contraseñas[4] = passwordEncoder.encode("admin");
        contraseñas[5] = passwordEncoder.encode("consultor");
        Arrays.stream(contraseñas).forEach(s -> System.out.println(s));
    }
}

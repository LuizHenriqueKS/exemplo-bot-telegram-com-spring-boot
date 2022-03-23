package br.com.zul.exemplobottelegramcomspringboot;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.zul.exemplobottelegramcomspringboot.service.TelegramBotService;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableScheduling
@Log4j2
public class ExemploBotTelegramComSpringBootApplication implements CommandLineRunner {

    @Autowired
    private TelegramBotService telegramBotService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExemploBotTelegramComSpringBootApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Entre com o token do bot: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String token = scanner.nextLine();
            if (token == null || token.isEmpty()) {
                log.error("Token não informado");
                System.exit(1);
            } else {
                log.info("Escutando o bot...");
                telegramBotService.setToken(token);
            }
        }
    }

}

package br.com.zul.exemplobottelegramcomspringboot.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zul.exemplobottelegramcomspringboot.domain.TelegramUpdate;
import br.com.zul.exemplobottelegramcomspringboot.service.TelegramBotService;
import br.com.zul.exemplobottelegramcomspringboot.service.TelegramMessageService;
import br.com.zul.exemplobottelegramcomspringboot.service.TelegramUpdateService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class TelegramUpdateConsumer {

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private TelegramUpdateService telegramUpdateService;

    @Autowired
    private TelegramMessageService telegramMessageService;

    @Scheduled(fixedDelayString = "${telegram.bot.delay-in-ms-between-updates}")
    public void consume() {
        try {
            if (telegramBotService.isAlready()) {
                List<TelegramUpdate> updates = telegramUpdateService.getLastUpdates();
                if (!updates.isEmpty()) {
                    log.info("Quantidade de updates recebidas: {}", updates.size());
                    updates.stream()
                            .filter(u -> u.getMessage() != null)
                            .map(u -> u.getMessage())
                            .forEach(telegramMessageService::addInQueue);
                }
            }
        } catch (Exception ex) {
            log.error("Erro ao consumir atualizações", ex);
            System.exit(1);
        }
    }

}

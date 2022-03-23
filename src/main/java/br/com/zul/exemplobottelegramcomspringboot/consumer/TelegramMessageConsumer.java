package br.com.zul.exemplobottelegramcomspringboot.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zul.exemplobottelegramcomspringboot.domain.SendMessage;
import br.com.zul.exemplobottelegramcomspringboot.domain.TelegramMessage;
import br.com.zul.exemplobottelegramcomspringboot.service.TelegramBotService;
import br.com.zul.exemplobottelegramcomspringboot.service.TelegramMessageService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class TelegramMessageConsumer {

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private TelegramMessageService telegramMessageService;

    @Value("${telegram.bot.messages-per-consumption}")
    private int messagesPerConsumption;

    @Scheduled(fixedDelayString = "${telegram.bot.messages-per-consumption}")
    public void consume() {
        if (telegramBotService.isAlready()) {
            for (int i = 0; i < messagesPerConsumption; i++) {
                TelegramMessage message = telegramMessageService.getNext().orElse(null);
                if (message == null)
                    break;
                try {
                    SendMessage messageToBeSent = SendMessage.builder()
                            .chatId(message.getChat().getId())
                            .text("Hi!")
                            .replyToMessageId(message.getMessageId())
                            .build();
                    telegramMessageService.send(messageToBeSent);
                } catch (Exception e) {
                    log.error(e);
                }
            }
        }
    }

}

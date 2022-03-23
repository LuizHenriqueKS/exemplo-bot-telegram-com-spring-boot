package br.com.zul.exemplobottelegramcomspringboot.service;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zul.exemplobottelegramcomspringboot.domain.SendMessage;
import br.com.zul.exemplobottelegramcomspringboot.domain.TelegramMessage;

@Service
public class TelegramMessageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TelegramBotService telegramBotService;

    private final Queue<TelegramMessage> queue = new LinkedBlockingDeque<>();

    public void addInQueue(TelegramMessage telegramMessage) {
        queue.add(telegramMessage);
    }

    public Optional<TelegramMessage> getNext() {
        return Optional.ofNullable(queue.poll());
    }

    public void send(SendMessage messageToBeSent) {
        String url = telegramBotService.getURLTemplate();
        Object[] uriVariables = { telegramBotService.getToken(), "sendMessage" };
        HttpEntity<SendMessage> requestEntity = new HttpEntity<>(messageToBeSent);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class, uriVariables);
    }

}

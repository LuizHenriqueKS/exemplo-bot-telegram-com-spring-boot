package br.com.zul.exemplobottelegramcomspringboot.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class TelegramBotService {

    private String token;

    public String getURLTemplate() {
        return "https://api.telegram.org/bot{token}/{endpoint}";
    }

    public boolean hasToken() {
        return token != null && !token.isEmpty();
    }

    public boolean isAlready() {
        return hasToken();
    }

}

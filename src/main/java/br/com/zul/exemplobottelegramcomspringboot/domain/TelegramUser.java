package br.com.zul.exemplobottelegramcomspringboot.domain;

import lombok.Data;

@Data
public class TelegramUser {

    private Long id;
    private Boolean isBot;
    private String firstName;
    private String username;

}

package br.com.zul.exemplobottelegramcomspringboot.domain;

import lombok.Data;

@Data
public class TelegramMessage {

    private Long messageId;
    private Long date;
    private TelegramUser from;
    private TelegramChat chat;
    private String text;

}

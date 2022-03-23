package br.com.zul.exemplobottelegramcomspringboot.domain;

import lombok.Data;

@Data
public class TelegramUpdate {

    private Long updateId;
    private TelegramMessage message;

}

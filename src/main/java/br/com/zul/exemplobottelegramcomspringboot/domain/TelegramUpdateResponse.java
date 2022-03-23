package br.com.zul.exemplobottelegramcomspringboot.domain;

import java.util.List;

import lombok.Data;

@Data
public class TelegramUpdateResponse {

    private boolean ok;
    private List<TelegramUpdate> result;

}

package br.com.zul.exemplobottelegramcomspringboot.domain;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessage {

    private Long chatId;
    private String text;

    @Nullable
    private Long replyToMessageId;

}

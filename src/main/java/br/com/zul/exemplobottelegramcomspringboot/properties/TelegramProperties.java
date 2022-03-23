package br.com.zul.exemplobottelegramcomspringboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "telegram")
public class TelegramProperties {

    public Bot bot;

    @Data
    public static class Bot {
        private int delayInMsBetweenUpdates;
        private int delayInMsBetweenMessages;
        private int messagesPerConsumption;
    }

}

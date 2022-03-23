package br.com.zul.exemplobottelegramcomspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zul.exemplobottelegramcomspringboot.domain.TelegramUpdate;
import br.com.zul.exemplobottelegramcomspringboot.domain.TelegramUpdateResponse;
import br.com.zul.exemplobottelegramcomspringboot.exception.InternalServerErrorException;

@Service
public class TelegramUpdateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TelegramBotService telegramBotService;

    private Long offset;

    public List<TelegramUpdate> getLastUpdates() {
        List<TelegramUpdate> updates = getUpdates(offset);
        offset = getLastUpdateId(updates, offset);
        if (offset != null && !updates.isEmpty())
            offset++;
        return updates;
    }

    private List<TelegramUpdate> getUpdates(@Nullable Long offset) {
        String url = telegramBotService.getURLTemplate();
        if (offset != null) {
            url += "?offset=" + offset;
        }
        Object[] uriVariables = { telegramBotService.getToken(), "getUpdates" };
        TelegramUpdateResponse response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                TelegramUpdateResponse.class,
                uriVariables)
                .getBody();
        if (response.isOk()) {
            return response.getResult();
        }
        throw new InternalServerErrorException("Erro ao consumir atualizações");
    }

    private Long getLastUpdateId(List<TelegramUpdate> updates, Long orElseValue) {
        return updates.stream()
                .map(u -> u.getUpdateId())
                .reduce(Math::max)
                .orElse(orElseValue);
    }

}

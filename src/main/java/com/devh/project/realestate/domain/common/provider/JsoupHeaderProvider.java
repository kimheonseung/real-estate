package com.devh.project.realestate.domain.common.provider;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class JsoupHeaderProvider {
    private final Map<String, String> header;

    public JsoupHeaderProvider() {
        this.header = new HashMap<>();
        this.header.put("Accept-Encoding", "gzip, deflate, br");
        this.header.put("Host", "new.land.naver.com");
        this.header.put("Sec-Fetch-Dest", "empty");
        this.header.put("Sec-Fetch-Mode", "cors");
        this.header.put("Sec-Fetch-Site", "same-origin");
        this.header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
    }

    public void update(String key, String value) {
        this.header.put(key, value);
    }

}

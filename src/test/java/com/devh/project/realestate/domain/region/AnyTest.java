package com.devh.project.realestate.domain.region;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnyTest {
    @Test
    void 아파트조회() throws IOException {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg5NjM1MDIsImV4cCI6MTY2ODk3NDMwMn0.Fo7WEw3h7qQxyltuCpu52UfAZQ6p1jmFgOwfVFRWy_A");
        headers.put("Host", "new.land.naver.com");
        headers.put("Referer", "https://new.land.naver.com/api/complexes/13956?a=APT:JGC:ABYG&b=A1&e=RETAIL");
        headers.put("Sec-Fetch-Dest", "empty");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");

        data.put("sameAddressGroup", "false");
        final String url = "https://new.land.naver.com/api/complexes/13956";
        Document document = Jsoup.connect(url)
                .headers(headers)
                .data(data)
                .ignoreContentType(true)
                .get().parser(Parser.xmlParser());



        System.out.println(document.body().text());
    }
}

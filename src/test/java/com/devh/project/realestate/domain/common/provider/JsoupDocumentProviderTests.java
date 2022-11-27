package com.devh.project.realestate.domain.common.provider;

import org.assertj.core.api.Assertions;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsoupDocumentProviderTests {
    @Autowired
    JsoupDocumentProvider jsoupDocumentProvider;

    @Test
    void 네이버_지역_시_조회()
    {
        // given
        String url = "https://new.land.naver.com/api/regions/list?cortarNo=0000000000";
        // when
        Document document = jsoupDocumentProvider.doGet(url);
        // then
        Assertions.assertThat(document).isNotNull();
    }
}

package com.devh.project.realestate.domain.common.embeddable.geo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinatesTests {
    @Test
    void 원시타입으로_생성_성공() {
        // given
        final float latitude = 37.3124395f;
        final float longitude = 42.3584939f;
        // when
        Coordinates coordinates = Coordinates.of(latitude, longitude);
        // then
        Assertions.assertThat(coordinates).isNotNull();
        Assertions.assertThat(coordinates.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(coordinates.getLongitude()).isEqualTo(longitude);
    }
}

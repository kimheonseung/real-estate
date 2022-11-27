package com.devh.project.realestate.domain.complex.vo.overview;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pyeong {
    private Area area;
    private float exclusive;
    private String name1;
    private String name2;

    private Pyeong(Area area, float exclusive, String name1, String name2) {
        this.area = area;
        this.exclusive = exclusive;
        this.name1 = name1;
        this.name2 = name2;
    }

    public static Pyeong of(Area area, float exclusive, String name1, String name2) {
        return new Pyeong(area, exclusive, name1, name2);
    }
}

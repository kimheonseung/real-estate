package com.devh.project.realestate.domain.region.entity;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("D")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dong extends Region {
    private Dong(
            String regionId,
            Region parent,
            Coordinates coordinates,
            String name) {
        super(regionId, parent, coordinates, name);
    }
    public static Dong create(
            String regionId,
            Region parent,
            Coordinates coordinates,
            String name) {
        return new Dong(regionId, parent, coordinates, name);
    }
}

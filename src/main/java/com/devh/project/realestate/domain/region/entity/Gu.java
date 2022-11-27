package com.devh.project.realestate.domain.region.entity;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("G")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gu extends Region {
    private Gu(
            String regionId,
            Region parent,
            Coordinates coordinates,
            String name) {
        super(regionId, parent, coordinates, name);
    }
    public static Gu create(
            String regionId,
            Region parent,
            Coordinates coordinates,
            String name) {
        return new Gu(regionId, parent, coordinates, name);
    }
}

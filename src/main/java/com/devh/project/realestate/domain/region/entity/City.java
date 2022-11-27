package com.devh.project.realestate.domain.region.entity;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City extends Region {
    private City(
            String id,
            Coordinates coordinates,
            String name) {
        super(id, null, coordinates, name);
    }
    public static City create(
            String id,
            Coordinates coordinates,
            String name) {
        return new City(id, coordinates, name);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

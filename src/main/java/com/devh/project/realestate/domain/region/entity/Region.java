package com.devh.project.realestate.domain.region.entity;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Region {
    @Id
    private String regionId;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Region parent;

    @Embedded
    private Coordinates coordinates;
    private String name;

    protected Region(
            String regionId,
            Region parent,
            Coordinates coordinates,
            String name
    ) {
        this.regionId = regionId;
        this.parent = parent;
        this.coordinates = coordinates;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId.equals(region.regionId) && parent.equals(region.parent) && coordinates.equals(region.coordinates) && name.equals(region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, parent, coordinates, name);
    }
}

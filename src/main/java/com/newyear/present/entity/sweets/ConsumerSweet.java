package com.newyear.present.entity.sweets;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "consumer_sweets", schema = "public", catalog = "newyearpackage")
public class ConsumerSweet extends Sweets {

    @Builder
    public ConsumerSweet(long id, String name, String type, Long wrapperWeight, Long fillingWeight,
                         SweetFilling sweetFillingByFillingId, SweetWrapper sweetWrapperByWrapperId) {
        super(id, name, type, wrapperWeight, fillingWeight, sweetFillingByFillingId, sweetWrapperByWrapperId);
    }

    @Override
    public String toString() {
        return "\nConsumerSweet " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", wrapperWeight=" + wrapperWeight +
                ", fillingWeight=" + fillingWeight;
    }
}

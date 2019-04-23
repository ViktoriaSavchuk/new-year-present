package com.newyear.present.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sweet_wrappers")
public class SweetWrapper {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "type", nullable = false)
    private String type;

    @Basic
    @Column(name = "sugar_in_1000_mg", nullable = true)
    private Long sugarIn1000Mg;

    @Override
    public String toString() {
        return "Wrapper:" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sugarIn1000Mg=" + sugarIn1000Mg +
    /*            ", consumerSweetsById=" + consumerSweetsById +
                ", sellerSweetsById=" + sellerSweetsById +*/
                "\n";
    }


}

package com.newyear.present.entity.sweets;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import lombok.*;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Sweets {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    protected String name;

    @Basic
    @Column(name = "type", nullable = false, length = -1)
    protected String type;

    @Basic
    @Column(name = "wrapper_weight", nullable = true)
    protected Long wrapperWeight;

    @Basic
    @Column(name = "filling_weight", nullable = true)
    protected Long fillingWeight;

    @ManyToOne
    @JoinColumn(name = "filling_id", referencedColumnName = "id")
    protected SweetFilling sweetFillingByFillingId;

    @ManyToOne
    @JoinColumn(name = "wrapper_id", referencedColumnName = "id")
    protected SweetWrapper sweetWrapperByWrapperId;


}

package com.newyear.present.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ready_packages", schema = "public", catalog = "newyearpackage")
public class ReadyPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "date", nullable = false)
    private Date date;

    @Basic
    @Column(name = "created_by", nullable = false, length = -1)
    private String createdBy;

    @Basic
    @Column(name = "weight", nullable = true, length = -1)
    private Long weight;

    @OneToMany(mappedBy = "readyPackageByPackageId")
    private Collection<SweetsPackages> sweetsPackagesById;


    @Override
    public String toString() {
        return
                "\n----------------------------------------------------------------------------------------" +
                        "\nPackage id-" + id +
                        " date:" + date +
                        " createdBy:'" + createdBy + '\'' +
                        "\nsweetsById:" + sweetsPackagesById +
                        "\n----------------------------------------------------------------------------------------";

    }


}

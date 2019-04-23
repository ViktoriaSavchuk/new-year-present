package com.newyear.present.entity;

import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.entity.sweets.SellerSweet;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sweets_packages", schema = "public", catalog = "newyearpackage")
public class SweetsPackages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "seller_sweet_id", referencedColumnName = "id")
    private SellerSweet sellerSweetsBySellerSweetId;

    @ManyToOne
    @JoinColumn(name = "consumer_sweet_id", referencedColumnName = "id")
    private ConsumerSweet consumerSweetsByConsumerSweetId;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private ReadyPackage readyPackageByPackageId;


    @Override
    public String toString() {
        if (this.sellerSweetsBySellerSweetId != null & this.consumerSweetsByConsumerSweetId != null) {
            return sellerSweetsBySellerSweetId +
                    "\n" + consumerSweetsByConsumerSweetId;
        } else if (sellerSweetsBySellerSweetId != null) {
            return sellerSweetsBySellerSweetId + "";
        } else if (consumerSweetsByConsumerSweetId != null) {
            return consumerSweetsByConsumerSweetId + "";
        }
        return "no sweets";
    }
}

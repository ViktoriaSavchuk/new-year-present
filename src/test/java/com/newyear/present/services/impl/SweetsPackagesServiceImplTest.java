package com.newyear.present.services.impl;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.entity.sweets.SellerSweet;
import com.newyear.present.repository.SweetsPackagesRepo;
import com.newyear.present.services.SweetsPackagesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SweetsPackagesServiceImplTest {

    @Mock
    SweetsPackagesRepo sweetsPackagesRepo;
    @InjectMocks
    SweetsPackagesServiceImpl sweetsPackagesService;

    private List<SweetsPackages> sweetsPackages = new ArrayList<>();

    private SweetsPackages sweetsPackage1;
    private SweetsPackages sweetsPackage2;
    private SweetsPackages sweetsPackage3;
    private SweetsPackages sweetsPackage4;
    private SweetsPackages sweetsPackage5;
    private SweetsPackages sweetsPackage6;
    private SweetsPackages sweetsPackage8;

    {

        sweetsPackages.add(sweetsPackage1);
        sweetsPackages.add(sweetsPackage2);
        sweetsPackages.add(sweetsPackage3);
        sweetsPackages.add(sweetsPackage4);
        sweetsPackages.add(sweetsPackage5);

        Calendar calendar = Calendar.getInstance();

        calendar.set(2019, 04, 15);
        Date date1 = new Date(calendar.getTimeInMillis());

        calendar.set(2019, 05, 16);
        Date date2 = new Date(calendar.getTimeInMillis());

        calendar.set(2019, 05, 17);
        Date date3 = new Date(calendar.getTimeInMillis());

        List<ReadyPackage> readyPackages = new ArrayList<>();


        ReadyPackage readyPackage1 = ReadyPackage.builder()
                .id(1L)
                .date(date1)
                .createdBy("seller")
                .weight(10500L)
                .build();

        ReadyPackage readyPackage2 = ReadyPackage.builder()
                .id(2L)
                .date(date1)
                .createdBy("seller")
                .build();

        ReadyPackage readyPackage3 = ReadyPackage.builder()
                .id(3L)
                .date(date1)
                .createdBy("consumer")
                .build();


        ReadyPackage readyPackage4 = ReadyPackage.builder()
                .id(-1)
                .date(null)
                .createdBy(null)
                .build();

        readyPackages.add(readyPackage1);
        readyPackages.add(readyPackage2);

        List<SweetFilling> sweetFillings = new ArrayList<>();


        SweetFilling sweetFilling1 = new SweetFilling(1L, "candied_roasted_nuts", "chocolate", 649L);
        SweetFilling sweetFilling2 = new SweetFilling(2L, "fudge_romashka", "chocolate", 669L);
        SweetFilling sweetFilling3 = new SweetFilling(3L, "crushed_peanuts", "chocolate", 654L);
        SweetFilling sweetFilling4 = new SweetFilling(4L, "fruit_jelly", "jelly", 790L);
        SweetFilling sweetFilling5 = new SweetFilling(4L, "powder", "caramel", 930L);

        sweetFillings.add(sweetFilling1);
        sweetFillings.add(sweetFilling2);
        sweetFillings.add(sweetFilling3);

        List<SweetWrapper> sweetWrappers = new ArrayList<>();

        SweetWrapper sweetWrapper1 = new SweetWrapper(1L, "white_choco", "chocolate", 590L);
        SweetWrapper sweetWrapper2 = new SweetWrapper(2L, "caramel", "caramel", 960L);
        SweetWrapper sweetWrapper3 = new SweetWrapper(3L, "jelly", "jelly", 8000L);

        sweetWrappers.add(sweetWrapper1);
        sweetWrappers.add(sweetWrapper2);
        sweetWrappers.add(sweetWrapper3);

        List<SellerSweet> sellerSweets = new ArrayList<>();

        SellerSweet sellerSweet1 = new SellerSweet(1L, "romashka",
                "chocolate", 1000L, 1500L, sweetFilling2, sweetWrapper1);
        SellerSweet sellerSweet2 = new SellerSweet(2L, "barbaris",
                "caramel", 8000L, null, null, sweetWrapper2);
        SellerSweet sellerSweet3 = new SellerSweet(3L, "bdzilka",
                "jelly", 3000L, 5000L, sweetFilling5, sweetWrapper3);


        sellerSweets.add(sellerSweet1);
        sellerSweets.add(sellerSweet2);

        List<ConsumerSweet> consumerSweets = new ArrayList<>();
        ConsumerSweet consumerSweet1 = new ConsumerSweet(1L, "customer_sweet_2019-04-19",
                "chocolate_jelly", 1400L, 2500L, sweetFilling1, sweetWrapper3);
        ConsumerSweet consumerSweet2 = new ConsumerSweet(2L, "customer_sweet_2019-04-19",
                "caramel_jelly", 1800L, 2000L, sweetFilling5, sweetWrapper3);
        ConsumerSweet consumerSweet3 = new ConsumerSweet(3L, "customer_sweet_2019-04-19",
                "chocolate_chocolate", 1500L, 2300L, sweetFilling3, sweetWrapper1);
        ConsumerSweet consumerSweet4 = new ConsumerSweet(-1, null, null, null, null, null, null);

        consumerSweets.add(consumerSweet1);
        consumerSweets.add(consumerSweet2);


        sweetsPackage1 = new SweetsPackages
                (1L, sellerSweet1, null, readyPackage1);

        sweetsPackage2 = new SweetsPackages
                (2L, sellerSweet2, null, readyPackage1);

        sweetsPackage3 = new SweetsPackages
                (3L, sellerSweet2, null, readyPackage2);

        sweetsPackage4 = new SweetsPackages
                (4L, sellerSweet3, null, readyPackage2);

        sweetsPackage5 = new SweetsPackages
                (5L, null, consumerSweet1, readyPackage2);

        sweetsPackage6 = new SweetsPackages
                (6L, null, consumerSweet3, readyPackage2);

        sweetsPackage8 = new SweetsPackages
                (-1, null, null, null);


    }

    @Test
    public void shouldReturnAllSweetsPackagesGroups() {
        when(sweetsPackagesRepo.findAll()).thenReturn(sweetsPackages);
        List<SweetsPackages> expected = sweetsPackages;
        List<SweetsPackages> actual = sweetsPackagesService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnySweetPackageGroup() {
        when(sweetsPackagesRepo.findAll()).thenThrow(RuntimeException.class);
        List<SweetsPackages> expected = null;
        List<SweetsPackages> actual = sweetsPackagesService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSweetPackageGroupsById() {
        when(sweetsPackagesRepo.getOne(2L)).thenReturn(sweetsPackage2);
        SweetsPackages actual = sweetsPackagesService.getOne(2L);
        SweetsPackages expected = sweetsPackage2;
        assertEquals(expected, actual);
    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetPackageGroupById() {
        when(sweetsPackagesRepo.getOne(7L)).thenThrow(RuntimeException.class);
        sweetsPackagesService.getOne(7L);
    }

    @Test
    public void sweetShouldBeSaved() {
        SweetsPackages sweetsPackage = sweetsPackage6;
        when(sweetsPackagesRepo.save(sweetsPackage)).thenReturn(sweetsPackage);
        sweetsPackagesService.save(sweetsPackage);
        verify(sweetsPackagesRepo).save(sweetsPackage);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotSaveNullValue() {
        when(sweetsPackagesRepo.save(sweetsPackage8)).thenThrow(RuntimeException.class);
        sweetsPackagesService.save(sweetsPackage8);
        verify(sweetsPackagesRepo, times(0)).save(sweetsPackage8);
        Mockito.verifyZeroInteractions(sweetsPackagesRepo);
    }


}
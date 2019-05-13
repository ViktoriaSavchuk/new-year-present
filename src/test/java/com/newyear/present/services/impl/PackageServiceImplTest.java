package com.newyear.present.services.impl;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.entity.sweets.SellerSweet;
import com.newyear.present.entity.sweets.Sweets;
import com.newyear.present.repository.ReadyPackageRepo;
import com.newyear.present.repository.SweetsPackagesRepo;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PackageServiceImplTest {

    @Mock
    private ReadyPackageRepo packageRepo;

    @Mock
    private SweetsPackagesRepo sweetsPackagesRepo;

    @InjectMocks
    private PackageServiceImpl packageService;


   /* @Before
    public void before() {
        packageService = Mockito.spy(new PackageServiceImpl(packageRepo, sweetsPackagesRepo));
    }
*/

    private List<ReadyPackage> readyPackages = new ArrayList<>();
    private ReadyPackage readyPackage1;
    private ReadyPackage readyPackage2;
    private ReadyPackage readyPackage3;
    private ReadyPackage readyPackage4;


    private List<SweetsPackages> sweetsPackages = new ArrayList<>();

    private List<Sweets> sweets = new ArrayList<>();
    private List<Sweets> expectedSortedList = new ArrayList<>();

    private List<Sweets> expectedSweetFromPackageWithSugarDiapasonFrom0To3g =new ArrayList<>();

    {

        Calendar calendar = Calendar.getInstance();

        calendar.set(2019, 04, 15);
        Date date1 = new Date(calendar.getTimeInMillis());

        calendar.set(2019, 05, 16);
        Date date2 = new Date(calendar.getTimeInMillis());

        calendar.set(2019, 05, 17);
        Date date3 = new Date(calendar.getTimeInMillis());


        readyPackage1 = ReadyPackage.builder()
                .id(1L)
                .date(date1)
                .createdBy("seller")
                .weight(10500L)
                .build();

        readyPackage2 = ReadyPackage.builder()
                .id(2L)
                .date(date2)
                .createdBy("seller")
                .build();

        readyPackage3 = ReadyPackage.builder()
                .id(3L)
                .date(date3)
                .createdBy("consumer")
                .build();


        readyPackage4 = ReadyPackage.builder()
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
        SweetWrapper sweetWrapper3 = new SweetWrapper(3L, "jelly", "jelly", 800L);

        sweetWrappers.add(sweetWrapper1);
        sweetWrappers.add(sweetWrapper2);
        sweetWrappers.add(sweetWrapper3);

        List<SellerSweet> sellerSweets = new ArrayList<>();

        SellerSweet sellerSweet1 = new SellerSweet(1L, "romashka",
                "chocolate", 10000L, 1500L, sweetFilling2, sweetWrapper1);//1003,5+5900=6903.5
        SellerSweet sellerSweet2 = new SellerSweet(2L, "barbaris",
                "caramel", 8000L, null, null, sweetWrapper2);//7680
        SellerSweet sellerSweet3 = new SellerSweet(3L, "bdzilka",
                "jelly", 3000L, 5500L, sweetFilling5, sweetWrapper3);//5115+2400=7515


        sellerSweets.add(sellerSweet1);
        sellerSweets.add(sellerSweet2);

        List<ConsumerSweet> consumerSweets = new ArrayList<>();
        ConsumerSweet consumerSweet1 = new ConsumerSweet(1L, "customer_sweet_2019-04-19",
                "chocolate_jelly", 1400L, 2500L, sweetFilling1, sweetWrapper3); //1622.5+1,120=2742.5
        ConsumerSweet consumerSweet2 = new ConsumerSweet(2L, "customer_sweet_2019-04-19",
                "caramel_jelly", 1800L, 2000L, sweetFilling5, sweetWrapper3);
        ConsumerSweet consumerSweet3 = new ConsumerSweet(3L, "customer_sweet_2019-04-19",
                "chocolate_chocolate", 1700L, 2300L, sweetFilling3, sweetWrapper1);//1504,2+1003=2507.2
        ConsumerSweet consumerSweet4 = new ConsumerSweet(-1, null,
                null, null, null, null, null);

        consumerSweets.add(consumerSweet1);
        consumerSweets.add(consumerSweet2);


        SweetsPackages sweetsPackages1 = new SweetsPackages
                (1L, sellerSweet1, null, readyPackage1);

        SweetsPackages sweetsPackages2 = new SweetsPackages
                (2L, sellerSweet2, null, readyPackage1);

        SweetsPackages sweetsPackages3 = new SweetsPackages
                (3L, sellerSweet2, null, readyPackage2);

        SweetsPackages sweetsPackages4 = new SweetsPackages
                (4L, sellerSweet3, null, readyPackage2);

        SweetsPackages sweetsPackages5 = new SweetsPackages
                (5L, null, consumerSweet1, readyPackage2);

        SweetsPackages sweetsPackages6 = new SweetsPackages
                (6L, null, consumerSweet3, readyPackage2);

        sweetsPackages.add(sweetsPackages1);
        sweetsPackages.add(sweetsPackages2);
        sweetsPackages.add(sweetsPackages3);
        sweetsPackages.add(sweetsPackages4);
        sweetsPackages.add(sweetsPackages5);
        sweetsPackages.add(sweetsPackages6);

        sweets.add(sellerSweet2);
        sweets.add(sellerSweet1);

        expectedSortedList.add(sellerSweet1);
        expectedSortedList.add(sellerSweet2);

        expectedSweetFromPackageWithSugarDiapasonFrom0To3g.add(consumerSweet1);
        expectedSweetFromPackageWithSugarDiapasonFrom0To3g.add(consumerSweet3);

    }

    @Test
    public void shouldReturnAllPackages() {
        when(packageRepo.findAll()).thenReturn(readyPackages);
        List<ReadyPackage> expected = readyPackages;
        List<ReadyPackage> actual = packageService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnySweet() {
        when(packageRepo.findAll()).thenThrow(RuntimeException.class);
        List<ReadyPackage> expected = null;
        List<ReadyPackage> actual = packageService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSweetById() {
        when(packageRepo.getOne(2L)).thenReturn(readyPackage2);
        ReadyPackage actual = packageService.getOne(2L);
        ReadyPackage expected = readyPackage2;
        assertEquals(expected, actual);
    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetById() {
        when(packageRepo.getOne(4L)).thenThrow(RuntimeException.class);
        packageService.getOne(4L);
    }

    @Test
    public void sweetShouldBeSaved() {
        ReadyPackage readyPackage = readyPackage2;
        when(packageRepo.save(readyPackage)).thenReturn(readyPackage);
        packageService.save(readyPackage);
        verify(packageRepo).save(readyPackage);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotSaveNullValue() {
        when(packageRepo.save(readyPackage4)).thenThrow(RuntimeException.class);
        packageService.save(readyPackage4);
        verify(packageRepo, times(0)).save(readyPackage4);
        Mockito.verifyZeroInteractions(packageRepo);
    }

    @Test
    public void updateWeight() {
        when(packageRepo.getOne(anyLong())).thenReturn(readyPackage2);
        packageService.updateWeight(2L);
        verify(packageRepo).getOne(anyLong());
    }


    @Test
    public void getWeight() {
        when(packageRepo.findById(1L)).thenReturn(Optional.ofNullable(readyPackage1));
        Long expected = 10500L;
        Long actual = packageService.getWeight(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void sortPackageBySweetsWeight() {

        when(sweetsPackagesRepo.findAll()).thenReturn(sweetsPackages);
        List<Sweets> actualSweets = packageService.sortPackageBySweetsWeight(1L);
        assertEquals(expectedSortedList, actualSweets);
    }

    @Test
    public void shouldFindSweetsBySugarDiapason() {

        when(sweetsPackagesRepo.findAll()).thenReturn(sweetsPackages);
        List<Sweets> actualSweets=packageService.findSweetsBySugarDiapason(0L,3000L,2L);
        assertEquals(expectedSweetFromPackageWithSugarDiapasonFrom0To3g,actualSweets);

    }
}
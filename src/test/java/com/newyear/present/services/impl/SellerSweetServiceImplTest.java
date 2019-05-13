package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.entity.sweets.SellerSweet;
import com.newyear.present.repository.SellerSweetsRepo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SellerSweetServiceImplTest {

    private List<SellerSweet> sellerSweets =new ArrayList<>();

    private SellerSweet sellerSweet1;
    private SellerSweet sellerSweet2;
    private SellerSweet sellerSweet3;

    {

        SweetFilling sweetFilling1 = new SweetFilling
                (1L, "candied_roasted_nuts", "chocolate", 649L);
        SweetFilling sweetFilling2 = new SweetFilling
                (2L, "fudge_romashka", "chocolate", 669L);
        SweetFilling sweetFilling3 = new SweetFilling
                (3L, "crushed_peanuts", "chocolate", 654L);
        SweetFilling sweetFilling4 = new SweetFilling
                (4L, "fruit_jelly", "jelly", 790L);
        SweetFilling sweetFilling5 = new SweetFilling
                (4L, "powder", "caramel", 930L);


        SweetWrapper sweetWrapper1 = new SweetWrapper(1L, "white_choco", "chocolate", 590L);
        SweetWrapper sweetWrapper2 = new SweetWrapper(2L, "caramel", "caramel", 960L);
        SweetWrapper sweetWrapper3 = new SweetWrapper(3L, "jelly", "jelly", 8000L);

        sellerSweet1 = new SellerSweet(1L, "romashka",
                "chocolate", 1000L, 1500L, sweetFilling2, sweetWrapper1);
        sellerSweet2 = new SellerSweet(2L, "barbaris",
                "caramel", 8000L, null, null, sweetWrapper2);
        sellerSweet3 = new SellerSweet(3L, "bdzilka",
                "jelly", 3000L, 5000L, sweetFilling5, sweetWrapper3);


        sellerSweets.add(sellerSweet1);
        sellerSweets.add(sellerSweet2);
    }


    @Mock
    SellerSweetsRepo sellerSweetsRepo;

    @InjectMocks
    SellerSweetServiceImpl sellerSweetService;

    @Test
    public void shouldReturnAllSweets() {
        when(sellerSweetsRepo.findAll()).thenReturn(sellerSweets);
        List<SellerSweet> expected = sellerSweets;
        List<SellerSweet> actual = sellerSweetService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnySweet() {
        when(sellerSweetsRepo.findAll()).thenThrow(RuntimeException.class);
        List<SellerSweet> expected = null;
        List<SellerSweet> actual = sellerSweetService.findAll();
        assertEquals(expected, actual);
    }


    @Test
    public void shouldReturnSweetById() {
        when(sellerSweetsRepo.getOne(2L)).thenReturn(sellerSweet2);
        SellerSweet actual = sellerSweetService.getOne(2L);
        SellerSweet expected = sellerSweet2;
        assertEquals(expected, actual);

    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetById() {
        when(sellerSweetsRepo.getOne(4L)).thenThrow(RuntimeException.class);
        sellerSweetService.getOne(4L);
    }


}
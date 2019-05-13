package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.repository.ConsumerSweetsRepo;
import com.newyear.present.services.impl.ConsumerSweetServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerSweetServiceImplTest {

    @Mock
    private ConsumerSweetsRepo consumerSweetsRepo;
    @InjectMocks
    private ConsumerSweetServiceImpl consumerSweetService;

    private List<ConsumerSweet> consumerSweets1 = new ArrayList<>();
    private List<ConsumerSweet> consumerSweets2 = new ArrayList<>();

    private ConsumerSweet consumerSweet1;
    private ConsumerSweet consumerSweet2;
    private ConsumerSweet consumerSweet3;
    private ConsumerSweet consumerSweet4;

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


        consumerSweet1 = new ConsumerSweet(1L, "customer_sweet_2019-04-19",
                "chocolate_jelly", 1400L, 2500L, sweetFilling1, sweetWrapper3);
        consumerSweet2 = new ConsumerSweet(2L, "customer_sweet_2019-04-19",
                "caramel_jelly", 1800L, 2000L, sweetFilling5, sweetWrapper3);
        consumerSweet3 = new ConsumerSweet(3L, "customer_sweet_2019-04-19",
                "chocolate_chocolate", 1500L, 2300L, sweetFilling3, sweetWrapper1);
        consumerSweet4 = new ConsumerSweet(-1, null, null, null, null, null, null);


        consumerSweets1.add(consumerSweet1);
        consumerSweets1.add(consumerSweet2);

    }

    @Test
    public void shouldReturnAllSweets() {
        when(consumerSweetsRepo.findAll()).thenReturn(consumerSweets1);
        List<ConsumerSweet> expected = consumerSweets1;
        List<ConsumerSweet> actual = consumerSweetService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnySweet() {
        when(consumerSweetsRepo.findAll()).thenThrow(RuntimeException.class);
        List<ConsumerSweet> expected = null;
        List<ConsumerSweet> actual = consumerSweetService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSweetById() {
        when(consumerSweetsRepo.getOne(2L)).thenReturn(consumerSweet2);
        ConsumerSweet actual = consumerSweetService.getOne(2L);
        ConsumerSweet expected = consumerSweet2;
        assertEquals(expected, actual);

    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetById() {
        when(consumerSweetsRepo.getOne(4L)).thenThrow(RuntimeException.class);
        consumerSweetService.getOne(4L);
    }

    @Test
    public void sweetShouldBeSaved() {
        ConsumerSweet consumerSweet = consumerSweet3;
        when(consumerSweetsRepo.save(consumerSweet)).thenReturn(consumerSweet);
        consumerSweetService.save(consumerSweet);
        verify(consumerSweetsRepo).save(consumerSweet);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotSaveNullValue() {
        when(consumerSweetsRepo.save(consumerSweet4)).thenThrow(RuntimeException.class);
        consumerSweetService.save(consumerSweet4);
        verify(consumerSweetsRepo, times(0)).save(consumerSweet4);
        Mockito.verifyZeroInteractions(consumerSweetsRepo);
    }
}
package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.repository.SweetFillingsRepo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SweetFillingServiceImplTest {

    @Mock
    private SweetFillingsRepo sweetFillingsRepo;
    @InjectMocks
    private SweetFillingServiceImpl sweetFillingService;

    private List<SweetFilling> sweetFillings1=new ArrayList<>();

    private SweetFilling sweetFilling1;
    private SweetFilling sweetFilling2;
    private SweetFilling sweetFilling3;

    {
        sweetFilling1 = new SweetFilling(1L, "candied_roasted_nuts", "chocolate", 649L);
        sweetFilling2 = new SweetFilling(2L, "fudge_romashka", "chocolate", 669L);
        sweetFilling3 = new SweetFilling(3L, "crushed_peanuts", "chocolate", 654L);

        sweetFillings1.add(sweetFilling1);
        sweetFillings1.add(sweetFilling2);
        sweetFillings1.add(sweetFilling3);
    }

    @Test
    public void shouldReturnSweetById() {
        when(sweetFillingsRepo.getOne(2L)).thenReturn(sweetFilling2);
        SweetFilling actual = sweetFillingService.getOne(2L);
        SweetFilling expected = sweetFilling2;
        assertEquals(expected, actual);
    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetById() {
        when(sweetFillingsRepo.getOne(4L)).thenThrow(RuntimeException.class);
        sweetFillingService.getOne(4L);
    }

    @Test
    public void shouldReturnAllFillings() {
        when(sweetFillingsRepo.findAll()).thenReturn(sweetFillings1);
        List<SweetFilling> expected = sweetFillings1;
        List<SweetFilling> actual = sweetFillingService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnyFillings() {
        when(sweetFillingsRepo.findAll()).thenThrow(RuntimeException.class);
        List<SweetFilling> expected = null;
        List<SweetFilling> actual = sweetFillingService.findAll();
        assertEquals(expected, actual);
    }


}
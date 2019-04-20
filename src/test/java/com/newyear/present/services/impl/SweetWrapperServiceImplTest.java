package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.repository.SweetWrappersRepo;
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
public class SweetWrapperServiceImplTest {

    @Mock
    SweetWrappersRepo sweetWrappersRepo;
    @InjectMocks
    SweetWrapperServiceImpl sweetWrapperService;

    private List<SweetWrapper> sweetWrappers = new ArrayList<>();

    private SweetWrapper sweetWrapper1;
    private SweetWrapper sweetWrapper2;
    private SweetWrapper sweetWrapper3;


    {
        sweetWrapper1 = new SweetWrapper(1L, "white_choco", "chocolate", 590L);
        sweetWrapper2 = new SweetWrapper(2L, "caramel", "caramel", 960L);
        sweetWrapper3 = new SweetWrapper(3L, "jelly", "jelly", 8000L);

        sweetWrappers.add(sweetWrapper1);
        sweetWrappers.add(sweetWrapper2);
        sweetWrappers.add(sweetWrapper3);
    }

    @Test
    public void shouldReturnSweetById() {
        when(sweetWrappersRepo.getOne(2L)).thenReturn(sweetWrapper2);
        SweetWrapper actual = sweetWrapperService.getOne(2L);
        SweetWrapper expected = sweetWrapper2;
        assertEquals(expected, actual);
    }


    @Test(expected = RuntimeException.class)
    public void shouldNotReturnSweetById() {
        when(sweetWrappersRepo.getOne(4L)).thenThrow(RuntimeException.class);
        sweetWrapperService.getOne(4L);
    }

    @Test
    public void shouldReturnAllFillings() {
        when(sweetWrappersRepo.findAll()).thenReturn(sweetWrappers);
        List<SweetWrapper> expected = sweetWrappers;
        List<SweetWrapper> actual = sweetWrapperService.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFindAnyFillings() {
        when(sweetWrappersRepo.findAll()).thenThrow(RuntimeException.class);
        List<SweetWrapper> expected = null;
        List<SweetWrapper> actual = sweetWrapperService.findAll();
        assertEquals(expected, actual);
    }

}
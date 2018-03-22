package com.example.queststore.services;

import com.example.queststore.dao.ExpLevelsDAO;
import com.example.queststore.views.ExpLevelsView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.when;

class ExpLevelsServiceTest {
    @Mock
    private ExpLevelsDAO mockDao;

    @Mock
    private ExpLevelsView mockView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddLevelOfExperience() {
        when(mockDao.add(any())).thenReturn(true);

        ExpLevelsService service = new ExpLevelsService(mockView, mockDao);
        boolean actual = service.addLevelOfExperience();
        Assertions.assertTrue(actual);
    }


}
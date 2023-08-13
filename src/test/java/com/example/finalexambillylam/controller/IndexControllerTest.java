package com.example.finalexambillylam.controller;

import static org.mockito.Mockito.*;
import com.example.finalexambillylam.controller.IndexController;
import com.example.finalexambillylam.entity.Salesman;
import com.example.finalexambillylam.repository.SalesmanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationWithTimeout;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndexControllerTest {

    @Mock
    private SalesmanRepository salesmanRepository;

    @Mock
    private Model model;

    @InjectMocks
    private IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHome() {
        List<Salesman> mockSalesmen = new ArrayList<>();
        // Add mocked Salesman objects to the list

        when(salesmanRepository.findAll()).thenReturn(mockSalesmen);

        String viewName = indexController.home(model);

        assertEquals("index", viewName); // Check if the correct view name is returned

        // Verify your assertions here based on the behavior you expect
    }

    @Test
    void testEditSalesman() {
        Long id = 1L;
        Salesman mockSalesman = new Salesman();
        // Set properties of the mocked Salesman object

        when(salesmanRepository.findById(id)).thenReturn(Optional.of(mockSalesman));

        String viewName = indexController.editSalesman(id, model);

        assertEquals("edit-form", viewName); // Check if the correct view name is returned
    }

    @Test
    void testDeleteSalesman() {
        Long id = 1L;

        String viewName = indexController.deleteSalesman(id);

        assertEquals("redirect:/", viewName); // Check if the correct view name is returned

        VerificationMode timesOnce = times(1);
        VerificationWithTimeout timeout = timeout(1000);

        verify(salesmanRepository, timesOnce).deleteById(id); // Verify that the delete method was called

    }

}

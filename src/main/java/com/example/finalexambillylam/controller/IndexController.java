package com.example.finalexambillylam.controller;

import com.example.finalexambillylam.entity.Salesman;
import com.example.finalexambillylam.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// GitHub Repo: https://github.com/BillyLam821/Salesman
@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class IndexController {

    @Autowired
    private SalesmanRepository salesmanRepository;

    // Lnading Page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("salesman", new Salesman());
        List<Salesman> salesmen = salesmanRepository.findAll();

        // Bottom List
        model.addAttribute("salesmen", salesmen);

        // Upper List
        List<Object[]> aggregatedData = salesmanRepository.sumAmountBySalesmanAndItem();
        model.addAttribute("aggregatedData", aggregatedData);

        return "index";
    }

    // Save Record
    @PostMapping("/save")
    public String saveEntity(@ModelAttribute Salesman entity) {
        salesmanRepository.save(entity);
        System.out.println("Saving entity: " + entity.name);
        return "redirect:/";
    }

    // Delete Record
    @GetMapping("/delete/{id}")
    public String deleteSalesman(@PathVariable Long id) {
        salesmanRepository.deleteById(id);
        return "redirect:/";
    }

    // Edit Record
    @GetMapping("/edit/{id}")
    public String editSalesman(@PathVariable Long id, Model model) {
        Optional<Salesman> salesman = salesmanRepository.findById(id);
        if (salesman.isPresent()) {
            model.addAttribute("salesman", salesman.get());
            return "edit-form"; //
        } else {
            return "redirect:/"; // Handle not found case
        }
    }
}

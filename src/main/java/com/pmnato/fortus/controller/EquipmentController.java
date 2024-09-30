package com.pmnato.fortus.controller;

import com.pmnato.fortus.entity.Equipment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {
    @GetMapping("equipment/all")
    public Equipment[] getAll() {
        return new Equipment[]{
                new Equipment(1, "Barra", 10, ";ImageUrl"),
                new Equipment(2, "Haletere 5", 5, ";ImageUrl"),
                new Equipment(3, "Haletere 10", 10, ";ImageUrl"),
                new Equipment(4, "Haletere 15", 15, ";ImageUrl"),
                new Equipment(5, "Haletere 20", 20, ";ImageUrl"),
                new Equipment(6, "Haletere 25", 25, ";ImageUrl"),
                new Equipment(7, "Haletere 30", 30, ";ImageUrl"),
                new Equipment(8, "Voador", 0, ";ImageUrl"),
                new Equipment(9, "Hack", 0, ";ImageUrl"),
                new Equipment(10, "Polia", 0, ";ImageUrl"),
                new Equipment(11, "Banco", 5, ";ImageUrl"),


        };
    }
}



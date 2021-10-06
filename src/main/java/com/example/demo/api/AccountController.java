package com.example.demo.api;

import com.example.demo.model.Miles;
import com.example.demo.model.MilesRequest;
import com.example.demo.service.MilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
    public class AccountController {

        private final MilesService milesService;

        @Autowired
        public AccountController(MilesService milesService) {
            this.milesService = milesService;
        }

        @PostMapping
        public void addMiles(@Valid @NonNull @RequestBody MilesRequest miles){
            milesService.addMiles(miles);
        }

        @GetMapping
        public List<Miles> getAllMiles(){
            return milesService.getAllMiles();
        }
    }


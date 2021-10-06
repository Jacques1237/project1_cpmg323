package com.example.demo.api;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import com.example.demo.model.Miles;
import com.example.demo.model.MilesRequest;
import com.example.demo.service.MilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/account")
@RestController
public class AccountController {

    private final MilesService milesService;

    @Autowired
    public AccountController(MilesService milesService) {
        this.milesService = milesService;
    }

    @PostMapping
    public void addMiles(@Valid @NonNull @RequestBody MilesRequest miles) {
        milesService.addMiles(miles);
    }

    @GetMapping(path = "{id}")
    public Miles getMilesById(@PathVariable("id") UUID id) {
        return milesService.getMilesById(id)
                .orElse(null);
    }


    @GetMapping
    public List<Miles> getAllMiles() {
        return milesService.getAllMiles();
    }
}


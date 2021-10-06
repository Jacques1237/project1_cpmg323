package com.example.demo.service;

import com.example.demo.dao.MilesDao;
import com.example.demo.model.Miles;
import com.example.demo.model.MilesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class MilesService {

    private final MilesDao milesDao;

    @Autowired
    public MilesService(@Qualifier("postgres2") MilesDao milesDao) {

        this.milesDao = milesDao;
    }

    public int addMiles(MilesRequest miles){

        return milesDao.insertMiles(miles);
    }

    public List<Miles> getAllMiles() {

        return milesDao.selectAllMiles();
    }
}




package com.example.demo.service;

import com.example.demo.dao.MilesDao;
import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import com.example.demo.model.Miles;
import com.example.demo.model.MilesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Miles> getMilesById(UUID id){
        return milesDao.selectMilesById(id);
    }

    public List<Miles> getAllMiles() {

        return milesDao.selectAllMiles();
    }

    public int subtractMiles(MilesRequest subMiles){

        return milesDao.subMiles(subMiles);
    }

}




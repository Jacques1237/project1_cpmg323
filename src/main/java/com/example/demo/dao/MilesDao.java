package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.Miles;
import com.example.demo.model.MilesRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MilesDao {

    int insertMiles(UUID id, Miles miles);

    default int insertMiles(MilesRequest miles){
        UUID id = UUID.randomUUID();
        return insertMiles(id, new Miles(id,miles.getPersonID(), miles.getMiles()));
    }

    Optional<Miles> selectMilesById(UUID id);

    List<Miles> selectAllMiles();

}


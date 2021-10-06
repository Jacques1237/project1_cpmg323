package com.example.demo.dao;

import com.example.demo.model.Miles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("milesDao")
public class MilesDataAccessService implements MilesDao {

    private static List<Miles> DB = new ArrayList<>();

    @Override
    public int insertMiles(UUID id, Miles miles) {
        DB.add(new Miles(id, miles.getPersonID(),miles.getMiles()));
        return 1;
    }


    @Override
    public Optional<Miles> selectMilesById(UUID id) {
        return DB.stream()
                .filter(miles -> miles.getId().equals(id))
                .findFirst();
    }


    @Override
    public List<Miles> selectAllMiles() {
        return DB;
    }

}

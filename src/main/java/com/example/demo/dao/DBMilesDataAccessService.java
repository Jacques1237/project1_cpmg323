package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import com.example.demo.model.Miles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres2")
public class DBMilesDataAccessService implements MilesDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBMilesDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertMiles(UUID id, Miles miles) {
        String sql = "" +
                "INSERT INTO account_type (" +
                " acc_id, " +
                " person_Id, " +
                " created_at, " +
                " acc_miles) " +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                miles.getPersonID(),
                miles.getCreated_date(),
                miles.getMiles()
        );

    }

    private RowMapper<Miles> mapMilesFromDb() {
        return (resultSet, i) -> {
            String milesId = resultSet.getString("acc_id");
            String personId = resultSet.getString("person_Id");
            UUID id = UUID.fromString(milesId);
            UUID personID = UUID.fromString(personId);
            LocalDateTime created_Date = resultSet.getTimestamp("created_at").toLocalDateTime();
            int Miles = resultSet.getInt("acc_miles");
            return new Miles(
                    id,
                    personID,
                    Miles,
                    created_Date

            );
        };
    }

    @Override
    public List<Miles> selectAllMiles() {

        final String sql = "" +
                "SELECT " +
                " account_type.acc_id, person.person_id" +
                " created_at, " +
                " acc_miles " +
                "FROM account_type " +
                "INNER JOIN person " +
                "ON account_type.person_Id = person.person_id";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String milesId = resultSet.getString("acc_id");
            String personId = resultSet.getString("person_Id");
            UUID id = UUID.fromString(milesId);
            UUID personID = UUID.fromString(personId);
            LocalDateTime created_Date = resultSet.getTimestamp("created_at").toLocalDateTime();
            int Miles = resultSet.getInt("acc_miles");
            return new Miles(id,personID, Miles, created_Date);
        });
    }
}



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
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                miles.getPersonID(),
                miles.getCreated_date(),
                miles.getMiles()
        );

    }


    @Override
    public Optional<Miles> selectMilesById(UUID id) {
        final String sql = "SELECT person_Id, acc_miles, created_at FROM account_type WHERE person_Id = ?";

        Miles mile = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("person_Id"));
            int miles =resultSet.getInt("acc_miles");
            LocalDateTime created_Date = resultSet.getTimestamp("created_at").toLocalDateTime();
            return new Miles(id,personId, miles, created_Date);
        });

        return Optional.ofNullable(mile);
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
                " acc_id," +
                " account_type.person_Id," +
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



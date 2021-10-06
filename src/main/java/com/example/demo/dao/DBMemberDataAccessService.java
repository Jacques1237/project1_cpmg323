package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class DBMemberDataAccessService implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBMemberDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Member member) {
        String sql = "" +
                "INSERT INTO person (" +
                " person_id, " +
                " name, " +
                " lastname, " +
                " person_miles)"+
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                member.getName(),
                member.getLastname(),
                member.getMiles()
        );

    }

    @Override
    public List<Member> selectAllPeople() {
        final String sql = "SELECT person_id, name, lastname, person_miles FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
           UUID id = UUID.fromString(resultSet.getString("person_id"));
           String name =resultSet.getString("name");
           String lastname =resultSet.getString("lastname");
           int miles =resultSet.getInt("person_miles");
           return new Member(id, name, lastname, miles);
        });
    }

    @Override
    public Optional<Member> selectPersonById(UUID id) {
        final String sql = "SELECT person_id, name, lastname, person_miles FROM person WHERE person_id = ?";

            Member member = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("person_id"));
            String name =resultSet.getString("name");
            String lastname =resultSet.getString("lastname");
            int miles =resultSet.getInt("person_miles");
            return new Member(personId, name, lastname, miles);
        });

            return Optional.ofNullable(member);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE person_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(UUID id, MemberRequest member) {
        final String sql = "UPDATE person SET name = ?, lastname = ? WHERE id = ?";
        return jdbcTemplate.update(sql, member.getName(),member.getLastname(), id);
    }

}

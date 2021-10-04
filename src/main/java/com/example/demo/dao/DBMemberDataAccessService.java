package com.example.demo.dao;

import com.example.demo.model.Member;
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
                " id, " +
                " name, " +
                " lastname, " +
                " transaction_type)"+
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                member.getName(),
                member.getLastname(),
                member.getTransaction_type()
        );

    }

    @Override
    public List<Member> selectAllPeople() {
        final String sql = "SELECT id, name, lastname, transaction_type FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
           UUID id = UUID.fromString(resultSet.getString("id"));
           String name =resultSet.getString("name");
           String lastname =resultSet.getString("lastname");
            String transaction_type =resultSet.getString("transaction_type");
           return new Member(id, name, lastname, transaction_type);
        });
    }

    @Override
    public Optional<Member> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, lastname, transaction_type FROM person WHERE id = ?";

            Member member = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name =resultSet.getString("name");
            String lastname =resultSet.getString("lastname");
                String transaction_type =resultSet.getString("transaction_type");
            return new Member(personId, name, lastname, transaction_type);
        });

            return Optional.ofNullable(member);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(UUID id, Member member) {
        final String sql = "UPDATE person SET name = ?, lastname = ?, transaction_type = ? WHERE id = ?";
        return jdbcTemplate.update(sql, member.getName(),member.getLastname(),member.getTransaction_type(), id);
    }

}

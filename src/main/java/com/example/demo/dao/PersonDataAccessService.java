package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String sql = "" +
                "INSERT INTO person (" +
                " id, " +
                " name, " +
                " lastname) " +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                person.getName(),
                person.getLastname()
        );

    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, lastname FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
           UUID id = UUID.fromString(resultSet.getString("id"));
           String name =resultSet.getString("name");
           String lastname =resultSet.getString("lastname");
           return new Person(id, name, lastname);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, lastname FROM person WHERE id = ?";

            Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name =resultSet.getString("name");
            String lastname =resultSet.getString("lastname");
            return new Person(personId, name, lastname);
        });

            return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}

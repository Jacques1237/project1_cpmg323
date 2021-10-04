package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberDao {
    int insertPerson(UUID id, Member member);

    default int insertPerson(MemberRequest person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, new Member(id, person.getName(), person.getLastname(), person.getTransaction_type()));
    }

    List<Member> selectAllPeople();

    Optional<Member> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Member member);
}
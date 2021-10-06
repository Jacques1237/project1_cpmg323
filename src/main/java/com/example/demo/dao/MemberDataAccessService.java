package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class MemberDataAccessService implements MemberDao {

    private static List<Member> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Member member) {
        DB.add(new Member(id, member.getName(), member.getLastname()));
        return 1;
    }

    @Override
    public List<Member> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Member> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Member> personMaybe = selectPersonById(id);
        if(personMaybe.isPresent() == false){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, MemberRequest update) {
        return selectPersonById(id)
                .map(person -> {
                   int indexOfPersonToUpdate = DB.indexOf(person);
                   if(indexOfPersonToUpdate >= 0){
                       DB.set(indexOfPersonToUpdate, new Member(id, update.getName(), update.getLastname()));
                               return 1;
                   }
                   return 0;
                })
                .orElse(0);

    }


}

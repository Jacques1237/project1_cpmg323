package com.example.demo.service;

import com.example.demo.dao.MemberDao;
import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberDao personDao;

    @Autowired
    public MemberService(@Qualifier("postgres") MemberDao personDao) {

        this.personDao = personDao;
    }

    public int addPerson(MemberRequest person){

        return personDao.insertPerson(person);
    }

    public List<Member> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Member> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int  deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int  updatePerson(UUID id, Member newMember){
        return personDao.updatePersonById(id, newMember);
    }
}

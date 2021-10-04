package com.example.demo.api;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRequest;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody MemberRequest person){
        memberService.addPerson(person);
    }

    @GetMapping
    public List<Member> getAllPeople(){
        return memberService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Member getPersonById(@PathVariable("id") UUID id){
        return memberService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
       memberService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @NonNull @RequestBody Member memberToUpdate){
        memberService.updatePerson(id, memberToUpdate);
    }
}

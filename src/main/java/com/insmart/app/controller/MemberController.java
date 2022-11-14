package com.insmart.app.controller;

import com.insmart.app.model.Member;
import com.insmart.app.service.MemberService;
import com.insmart.app.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;
    @PostMapping("/create")
    public Member save(@RequestBody Member member,@PathVariable String token) {
        return memberService.save(member,token);
    }
    @PostMapping("/client/create")
    public Member saveClient(@RequestBody Member member,@PathVariable String token) {
        return memberService.save(member,token);
    }
    @GetMapping("/get/{id}")
    public Member findById(@PathVariable Long id,@PathVariable String token) {
        return memberService.findById(id,token);
    }
    @GetMapping("/get")
    public List<Member> findAll(@PathVariable String token) {
        return memberService.findAll(token);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id,@PathVariable String token) {
        memberService.deleteById(id,token);
    }
    @PutMapping("/update")
    public Member update(@RequestBody Member member) {
        return memberService.update(member);
    }
    @GetMapping("/get/{page}/{size}")
    public org.springframework.data.domain.Page<Member> findAll(@PathVariable int page, @PathVariable int size,@PathVariable String token) {
        return memberService.findAll(page, size,token);
    }
    @GetMapping("/username/{username}")
    public Member findByUsername(@PathVariable String username,String token) {
        return memberService.findByUsername(username,token);
    }
    @GetMapping("/get-multi/")
    public List<Member> findMemberByIds(@RequestBody List<Long> ids,String token) {
        return memberService.findMemberByIds(ids,token);
    }
    @PostMapping("/login")
    public com.insmart.app.bean.ResponseData login(@RequestBody Member member) {
        return memberService.login(member);
    }
    @GetMapping("/response/member")
    public List<com.insmart.app.bean.ResponseMember> findResponseMember(@PathVariable String token) {
        return memberService.findResponseMember(token);
    }
}

package com.insmart.app.service;
import com.insmart.app.bean.ResponseData;
import com.insmart.app.bean.ResponseMember;
import com.insmart.app.model.Member;
import com.insmart.app.model.Token;
import com.insmart.app.repository.MemberRepository;
import com.insmart.app.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TokenRepository tokenRepository;

    public Member save(Member member,String token) {

        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }return memberRepository.save(member);
    }
    public Member findById(Long id,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return memberRepository.findById(id).orElseThrow();
    }
    public List<Member> findAll(String token) {

        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return memberRepository.findAll();
    }
    public void deleteById(Long id,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        memberRepository.deleteById(id);
    }
    public Member update(Member member) {
        return memberRepository.save(member);
    }
    public Page<Member> findAll(int page, int size,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return memberRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
    public ResponseData login(Member member)  {
        for(Member m: memberRepository.findAll()) {
            if(m.getUsername().equals(member.getUsername()) && m.getPassword().equals(member.getPassword())) {
                return new ResponseData<>("Login success","", m, "Successfully", HttpStatus.OK.value());
            }
        }
        return new ResponseData<>("Login failed",null, null, "Failed",HttpStatus.NO_CONTENT.value());
    }
    public Member findByUsername(String username,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return memberRepository.findByUsername(username);
    }
    public List<ResponseMember> findResponseMember(String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
       List<ResponseMember> responseMembers = new LinkedList<>();
       for(Member m: memberRepository.findAll()) {
           responseMembers.add(new ResponseMember(m.getId(), m.getName()));
       }
       return responseMembers;
    }
    public List<Member> findMemberByIds(List<Long> ids,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return memberRepository.findMemberByIds(ids);
    }
}

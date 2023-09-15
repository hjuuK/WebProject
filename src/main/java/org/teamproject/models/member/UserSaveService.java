package org.teamproject.models.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.teamproject.controllers.member.JoinForm;
import org.teamproject.entities.Member;
import org.teamproject.repositories.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserSaveService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder;

    public void save(JoinForm joinForm, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Member member = new ModelMapper().map(joinForm, Member.class);
        save(member);
    }

    public void save(Member member) {
        String userPw = member.getUserPw();
        if (userPw != null && !userPw.isBlank()) {
            member.setUserPw(encoder.encode(userPw));
        }

        repository.saveAndFlush(member);
    }
}

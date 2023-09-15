package org.teamproject.controllers.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamproject.repositories.MemberRepository;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {
    private final MemberRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm form = (JoinForm)target;

        /**
         * 1. 아이디(이메일) 중복 여부 체크
         * 2. 비밀번호와 비밀번호확인 일치 여부 체크
         * 3. 휴대전화번호가 있으면 형식 체크
         */

        // 1. 아이디(이메일) 중복 여부 체크
        String email = form.getEmail();
        if (email != null && !email.isBlank() && repository.exists(email)) {
            errors.rejectValue("email", "Duplicated");
        }

        // 2. 비밀번호와 비밀번호확인 일치 여부 체크
        String userPw = form.getUserPw();
        String userPwRe = form.getUserPwRe();
        if (userPw != null && !userPw.isBlank() && userPwRe != null && !userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "mismatch");
        }

        // 3. 휴대전화번호가 있으면 형식 체크
        String mobile = form.getMobile();

    }
}

package weteam.backend.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.domain.auth.entity.Auth;
import weteam.backend.domain.auth.dto.JoinDto;
import weteam.backend.domain.auth.repository.AuthRepository;
import weteam.backend.application.ExceptionMessage;
import weteam.backend.domain.member.MemberService;
import weteam.backend.domain.member.entity.Member;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    public Optional<Auth> findById(Long id) {
        return authRepository.findById(id);
    }

    public void join(JoinDto joinDto) {
        if (authRepository.findByUid(joinDto.uid()).isPresent()) {
            throw new DuplicateKeyException(ExceptionMessage.DUPLICATE.getMessage());
        }
        Member member = memberService.create(Member.from(joinDto));
        String hashedPassword = passwordEncoder.encode(joinDto.password());
        authRepository.save(new Auth(member, hashedPassword, joinDto.uid()));
    }

    public void verifyUid(String uid) {
        if (authRepository.findByUid(uid).isPresent()) {
            throw new DuplicateKeyException(ExceptionMessage.DUPLICATE.getMessage());
        }
    }

    public void verifyNickname(String nickname) {
        if (memberService.findByNickname(nickname).isPresent()) {
            throw new DuplicateKeyException(ExceptionMessage.DUPLICATE.getMessage());
        }
    }
}
package weteam.backend.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.config.dto.Message;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;
import weteam.backend.member.mapper.MemberMapper;
import weteam.backend.config.jwt.util.SecurityUtil;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "다른 사용자 조회")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public Message<MemberDto.Res> findById(@PathVariable("id") Long id) {
        Member member = memberService.findProfile(id);
        MemberDto.Res res = MemberMapper.instance.toRes(member);
        return new Message<>(res);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "내 정보 조회")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public Message<MemberDto.Res> findMyInfo() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberService.findProfile(memberId);
        MemberDto.Res res = MemberMapper.instance.toRes(member);
        return new Message<>(res);
    }

    @PatchMapping("/{organization}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "사용자 소속 변경")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public Message<String> updateOrganization(@PathVariable("organization") String organization) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        memberService.updateOrganization(memberId, organization);
        return new Message<>("사용자 소속 변경 성공");
    }
}

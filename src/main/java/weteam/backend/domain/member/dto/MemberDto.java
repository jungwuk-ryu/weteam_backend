package weteam.backend.domain.member.dto;

import lombok.Builder;
import weteam.backend.domain.hashtag.dto.HashtagDto;
import weteam.backend.domain.member.entity.Member;

import java.util.List;


@Builder
public record MemberDto(
        Long id,
        String nickname,
        String username,
        String organization,
        List<HashtagDto> hashtagList) {
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .username(member.getUsername())
                        .organization(member.getOrganization())
                        .hashtagList(HashtagDto.from(member.getMemberHashtagList()))
                        .build();
    }
}
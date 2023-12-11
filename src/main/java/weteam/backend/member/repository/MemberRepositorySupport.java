package weteam.backend.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import weteam.backend.hashtag.domain.MemberHashtag;
import weteam.backend.member.domain.Member;

import static weteam.backend.hashtag.domain.QHashtag.hashtag;
import static weteam.backend.hashtag.domain.QMemberHashtag.memberHashtag;
import static weteam.backend.member.domain.QMember.member;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public Member findProfile(Long memberId) {
        return queryFactory.selectFrom(member)
                           .leftJoin(member.memberHashtagList, memberHashtag).fetchJoin()
                           .leftJoin(memberHashtag.hashtag, hashtag).fetchJoin()
                           .where(member.id.eq(memberId),
                                   memberHashtag.isUse.isTrue().or(memberHashtag.isNull()))
                           .distinct()
                           .fetchOne();
    }
}
package weteam.backend.schedule.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import weteam.backend.hashtag.domain.MemberHashtag;
import weteam.backend.schedule.domain.MemberSchedule;

import java.time.LocalDateTime;
import java.util.List;

import static weteam.backend.schedule.domain.QMemberSchedule.memberSchedule;

@Repository
public class MemberScheduleRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberScheduleRepositorySupport(JPAQueryFactory queryFactory) {
        super(MemberSchedule.class);
        this.queryFactory = queryFactory;
    }

    public List<MemberSchedule> findByMonth(LocalDateTime startDate, LocalDateTime endDate, Long memberId) {
        return queryFactory.selectFrom(memberSchedule)
                           .where(memberSchedule.startedAt.between(startDate, endDate),
                                   memberSchedule.member.id.eq(memberId))
                           .orderBy(memberSchedule.startedAt.asc())
                           .fetch();
    }

    public List<MemberSchedule> findByDate(LocalDateTime startDate, LocalDateTime endDate, Long memberId) {
        return queryFactory.selectFrom(memberSchedule)
                           .where(memberSchedule.startedAt.between(startDate, endDate),
                                   memberSchedule.member.id.eq(memberId))
                           .orderBy(memberSchedule.startedAt.asc())
                           .fetch();
    }
}

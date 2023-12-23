package weteam.backend.domain.hashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.domain.hashtag.domain.Hashtag;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
    Optional<Hashtag> findByName(String name);
}
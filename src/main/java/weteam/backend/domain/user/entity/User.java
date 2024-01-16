package weteam.backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import weteam.backend.application.BaseEntity;
import weteam.backend.domain.hashtag.Hashtag;
import weteam.backend.domain.profile.ProfileImage;
import weteam.backend.domain.project.entity.Project;
import weteam.backend.domain.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, organization, uid, email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileImage profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Project> projectList = new ArrayList<>();

    public static User from(final Long id) {
        return User.builder().id(id).build();
    }

    public static User from(final UserDto userDto) {
        return User.builder().id(userDto.id())
                   .username(userDto.username())
                   .email(userDto.email())
                   .organization(userDto.organization())
                   .role(userDto.role())
                   .build();
    }

    public void updateOrganization(String organization) {
        this.organization = organization;
    }
}

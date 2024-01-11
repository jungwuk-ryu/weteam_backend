package weteam.backend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.application.Message;
import weteam.backend.application.handler.exception.DuplicateKeyException;
import weteam.backend.application.handler.exception.NotFoundException;
import weteam.backend.domain.project.dto.ProjectMemberDto;
import weteam.backend.domain.project.entity.ProjectUser;
import weteam.backend.domain.project.param.UpdateProjectRoleParam;
import weteam.backend.domain.project.repository.ProjectMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectUserService {
    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMemberDto> findUsersByProjectId(final Long projectId) {
        final List<ProjectUser> projectUserList = projectMemberRepository.findByProjectId(projectId);
        if (projectUserList.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }
        return ProjectMemberDto.from(projectUserList);
    }

    @Transactional
    public void acceptInvite(final Long projectId, final Long userId) {
        if (projectMemberRepository.findByProjectIdAndUserId(projectId, userId).isPresent()) {
            throw new DuplicateKeyException(Message.DUPLICATE);
        }
        projectMemberRepository.save(ProjectUser.from(projectId, userId));
    }

    @Transactional
    public void updateProjectRole(final UpdateProjectRoleParam param, final Long userId) {
        ProjectUser projectUser = projectMemberRepository.findByProjectIdAndUserId(param.getProjectId(), userId).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        projectUser.updateRole(param.getRole());
    }
}
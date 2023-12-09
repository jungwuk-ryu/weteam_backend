package weteam.backend.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import weteam.backend.config.dto.Message;
import weteam.backend.project.domain.Project;
import weteam.backend.project.dto.ProjectDto;
import weteam.backend.project.mapper.ProjectMapper;
import weteam.backend.config.jwt.util.SecurityUtil;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Group Project", description = "group project API")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "팀플 생성", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    public Message<ProjectDto.Res> createProject(@RequestBody @Valid ProjectDto.Create request) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Project entity = ProjectMapper.instance.toEntity(request);
        ProjectDto.Res res= projectService.createProject(memberId, entity);
        return new Message<>(HttpStatus.CREATED, res);
    }

    @GetMapping("/invite/{projectId}")
    public Message<?> acceptInvite(@PathVariable("projectId") Long projectId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return Message.builder()
                      .result(true)
                      .httpStatus(HttpStatus.OK)
                      .message("팀플 초대 성공")
                      .build();
    }
}

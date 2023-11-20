package weteam.backend.hash_tag.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class HashtagDto {
    @Getter
    @Builder
    public static class Create {
        @NotBlank(message = "name 누락")
        @Schema(description = "해시태그 이름", nullable = false, example = "밤샘인간")
        private String name;
        
        @Min(value = 1, message = "type은 1~4")
        @Max(value = 4, message = "type은 1~4")
        @Schema(description = "해시태그 타입 1: 희망업무, 2: mbti, 3: 특기, 4: 성격, 5: 기타", nullable = false, example = "1")
        private int type;
    }
    @Getter
    @Builder
    @ToString
    public static class Res{
        private Long id;
        private String name;
        private int type;
        private boolean isUse;
    }

    @Getter
    @Builder
    public static class ResList {
        private List<Res> resList = new ArrayList<>();
    }
}
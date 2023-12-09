package study.developia.mysql.domain.member.dto;

import java.time.LocalDateTime;

public record PostDto(Long id, String content, LocalDateTime createdAt, Long likeCount) {
}

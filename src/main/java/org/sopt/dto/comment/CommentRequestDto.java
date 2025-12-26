package org.sopt.dto.comment;

public class CommentRequestDto {
    public record create(
            Long articleId,
            String content,
            Long memberId
    ){}

    public record update(
            Long articleId,
            String content,
            Long commentId
    ) {}

    public record delete(
            Long commentId
    ) {}
}

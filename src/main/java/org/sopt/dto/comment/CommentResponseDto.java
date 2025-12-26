package org.sopt.dto.comment;

import org.sopt.domain.Comment;

import java.util.List;

public class CommentResponseDto {
    public record CommentInfo(
            String content
    ){}

    public record findComments(
            List<CommentInfo> comments
    ) {
        public static findComments from(List<Comment> comments) {
            return new findComments(comments.stream()
                    .map( comment -> new CommentInfo(comment.getContent()))
                    .toList()
            );
        }
    }
}

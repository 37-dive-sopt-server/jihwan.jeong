package org.sopt.dto.article;

public class ArticleRequestDto {
    public record create(
            Long memberId,
            String tag,
            String title,
            String content
    ){}
    public record findArticleByArticleId(Long articleId) {}
}

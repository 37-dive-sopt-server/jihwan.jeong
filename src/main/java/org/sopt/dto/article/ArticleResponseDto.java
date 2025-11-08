package org.sopt.dto.article;

import org.sopt.domain.Article;

import java.util.List;

public class ArticleResponseDto {
    public record ArticleInfo(
            long articleId,
            String title,
            String tag,
            String createdAt,
            String content
    ) {
       public static ArticleInfo from(Article article) {
           return new ArticleInfo(
                   article.getId(),
                   article.getTitle(),
                   article.getTag().toString(),
                   article.getCreatedAt().toString(),
                   article.getContent()
           );
       }
    }

    public record ArticleInfos(
            List<ArticleInfo> articleInfos
    ) {
        public static ArticleInfos from(List<Article> articles) {
            return new ArticleInfos(
                    articles.stream().map(
                                    ArticleInfo::from
                            )
                            .toList()
            );
        }
    }
}

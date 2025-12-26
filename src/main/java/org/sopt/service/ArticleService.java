package org.sopt.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Article;
import org.sopt.domain.Comment;
import org.sopt.domain.Member;
import org.sopt.dto.article.ArticleRequestDto;
import org.sopt.dto.article.ArticleResponseDto;
import org.sopt.dto.comment.CommentRequestDto;
import org.sopt.dto.comment.CommentResponseDto;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public ArticleResponseDto.ArticleInfo findArticleByArticleId(ArticleRequestDto.findArticleByArticleId requestDto) {
        Long articleId = requestDto.articleId();
        Article article = articleRepository.findById(articleId).orElse(null);
        return ArticleResponseDto.ArticleInfo.from(article);
    }

    public ArticleResponseDto.ArticleInfos findAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return ArticleResponseDto.ArticleInfos.from(articles);
    }

    public ArticleResponseDto.ArticleInfo createArticle(ArticleRequestDto.create requestDto) {
        Long memberId = requestDto.memberId();
        String tag = requestDto.tag();
        String title = requestDto.title();
        String content = requestDto.content();

        Member member = memberRepository.findById(memberId).orElse(null);

        Article article = new Article(tag, LocalDate.now(), title, content, member);
        articleRepository.save(article);

        return ArticleResponseDto.ArticleInfo.from(article);
    }

    public CommentResponseDto.CommentInfo createComment(CommentRequestDto.create requestDto) {
        Long articleId = requestDto.articleId();
        String content = requestDto.content();
        Long memberId = requestDto.memberId();

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아티클 ID 입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 ID 입니다."));

        Comment comment = new Comment(content, article, member);
        commentRepository.save(comment);

        return new CommentResponseDto.CommentInfo(comment.getContent());
    }

    public CommentResponseDto.findComments findComments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return CommentResponseDto.findComments.from(comments);
    }

    public CommentResponseDto.CommentInfo updateComment(CommentRequestDto.update requestDto) {
        Comment comment = commentRepository.findById(requestDto.commentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        comment.updateContent(requestDto.content());
        return new CommentResponseDto.CommentInfo(comment.getContent());
    }

    public void deleteComment(CommentRequestDto.delete requestDto) {
        Comment comment = commentRepository.findById(requestDto.commentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        commentRepository.delete(comment);
    }
}

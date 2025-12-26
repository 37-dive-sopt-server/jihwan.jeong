package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.dto.article.ArticleRequestDto;
import org.sopt.dto.article.ArticleResponseDto;
import org.sopt.dto.comment.CommentRequestDto;
import org.sopt.dto.comment.CommentResponseDto;
import org.sopt.global.ApiResponse;
import org.sopt.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<?> createArticle(
            @RequestBody ArticleRequestDto.create requestDto
    ) {
        ArticleResponseDto.ArticleInfo body = articleService.createArticle(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "아티클 생성 성공", body));
    }

    @GetMapping
    public ResponseEntity<?> findAllArticles() {
        ArticleResponseDto.ArticleInfos body = articleService.findAllArticles();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "전제 아티클 조회 성공", body));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> findArticleById(
            @PathVariable Long articleId
    ) {
        ArticleRequestDto.findArticleByArticleId requestDto = new ArticleRequestDto.findArticleByArticleId(articleId);
        ArticleResponseDto.ArticleInfo body = articleService.findArticleByArticleId(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,"아티클 조회 성공",body));
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable Long articleId,
            @RequestBody String content,
            @RequestBody Long memberId
    ) {
        CommentRequestDto.create requestDto = new CommentRequestDto.create(articleId, content, memberId);
        CommentResponseDto.CommentInfo body = articleService.createComment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "댓글 작성 성공", body));
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<?> findComments(
            @PathVariable Long articleId
    ) {
        CommentResponseDto.findComments body = articleService.findComments(articleId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "댓글 조회 성공", body));
    }

    @PatchMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @RequestBody String content
    ) {
        CommentRequestDto.update requestDto = new CommentRequestDto.update(articleId, content, commentId);
        CommentResponseDto.CommentInfo body = articleService.updateComment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "댓글 수정 성공", body));
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId
    ) {
        CommentRequestDto.delete requestDto = new CommentRequestDto.delete(commentId);
        articleService.deleteComment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "댓글 삭제 성공", null));
    }
}

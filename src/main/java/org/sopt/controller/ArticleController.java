package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.dto.article.ArticleRequestDto;
import org.sopt.dto.article.ArticleResponseDto;
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

}

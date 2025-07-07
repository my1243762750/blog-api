package com.meiyang.blog_api.controller;

import com.meiyang.blog_api.entity.Post;
import com.meiyang.blog_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
@Tag(name = "博客文章管理", description = "博客文章的增删改查接口")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @Operation(summary = "获取所有文章", description = "按创建时间倒序返回所有博客文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取文章列表"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    
    @Operation(summary = "根据ID获取文章", description = "根据文章ID获取指定的博客文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取文章"),
        @ApiResponse(responseCode = "404", description = "文章不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "创建新文章", description = "创建一篇新的博客文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "文章创建成功",
                content = @Content(schema = @Schema(implementation = Post.class))),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @PostMapping
    public ResponseEntity<Post> createPost(
            @Parameter(description = "文章信息") @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
    
    @Operation(summary = "更新文章", description = "根据ID更新指定的博客文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "文章更新成功",
                content = @Content(schema = @Schema(implementation = Post.class))),
        @ApiResponse(responseCode = "404", description = "文章不存在"),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id,
            @Parameter(description = "更新的文章信息") @RequestBody Post postDetails) {
        Optional<Post> updatedPost = postService.updatePost(id, postDetails);
        return updatedPost.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "删除文章", description = "根据ID删除指定的博客文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "文章删除成功"),
        @ApiResponse(responseCode = "404", description = "文章不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id) {
        boolean deleted = postService.deletePost(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "根据作者获取文章", description = "根据作者名称获取所有相关文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取文章列表"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Post>> getPostsByAuthor(
            @Parameter(description = "作者名称", example = "张三") @PathVariable String author) {
        List<Post> posts = postService.getPostsByAuthor(author);
        return ResponseEntity.ok(posts);
    }
    
    @Operation(summary = "搜索文章", description = "根据标题关键词搜索文章")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取搜索结果"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPostsByTitle(
            @Parameter(description = "搜索关键词", example = "Spring Boot") @RequestParam String title) {
        List<Post> posts = postService.searchPostsByTitle(title);
        return ResponseEntity.ok(posts);
    }
    
    @Operation(summary = "健康检查", description = "检查博客API服务状态")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "服务正常运行")
    })
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Blog API is running!");
    }
} 
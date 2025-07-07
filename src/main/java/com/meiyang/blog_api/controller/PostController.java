package com.meiyang.blog_api.controller;

import com.meiyang.blog_api.entity.Post;
import com.meiyang.blog_api.service.PostService;
import com.meiyang.blog_api.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取文章列表"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping
    public ApiResponse<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ApiResponse.success("获取文章列表成功", posts);
    }
    
    @Operation(summary = "根据ID获取文章", description = "根据文章ID获取指定的博客文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取文章"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "文章不存在"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/{id}")
    public ApiResponse<Post> getPostById(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            return ApiResponse.success("获取文章成功", post.get());
        } else {
            return ApiResponse.notFound("文章不存在");
        }
    }
    
    @Operation(summary = "创建新文章", description = "创建一篇新的博客文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "文章创建成功",
                content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "请求参数错误"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @PostMapping
    public ApiResponse<Post> createPost(
            @Parameter(description = "文章信息") @RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(post);
            return ApiResponse.success("文章创建成功", createdPost);
        } catch (Exception e) {
            return ApiResponse.badRequest("创建文章失败: " + e.getMessage());
        }
    }
    
    @Operation(summary = "更新文章", description = "根据ID更新指定的博客文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "文章更新成功",
                content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "文章不存在"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "请求参数错误"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @PutMapping("/{id}")
    public ApiResponse<Post> updatePost(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id,
            @Parameter(description = "更新的文章信息") @RequestBody Post postDetails) {
        Optional<Post> updatedPost = postService.updatePost(id, postDetails);
        if (updatedPost.isPresent()) {
            return ApiResponse.success("文章更新成功", updatedPost.get());
        } else {
            return ApiResponse.notFound("文章不存在");
        }
    }
    
    @Operation(summary = "删除文章", description = "根据ID删除指定的博客文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "文章删除成功"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "文章不存在"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long id) {
        boolean deleted = postService.deletePost(id);
        if (deleted) {
            return ApiResponse.success("文章删除成功", null);
        } else {
            return ApiResponse.notFound("文章不存在");
        }
    }
    
    @Operation(summary = "根据作者获取文章", description = "根据作者名称获取所有相关文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取文章列表"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/author/{author}")
    public ApiResponse<List<Post>> getPostsByAuthor(
            @Parameter(description = "作者名称", example = "张三") @PathVariable String author) {
        List<Post> posts = postService.getPostsByAuthor(author);
        return ApiResponse.success("获取作者文章成功", posts);
    }
    
    @Operation(summary = "搜索文章", description = "根据标题关键词搜索文章")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取搜索结果"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/search")
    public ApiResponse<List<Post>> searchPostsByTitle(
            @Parameter(description = "搜索关键词", example = "Spring Boot") @RequestParam String title) {
        List<Post> posts = postService.searchPostsByTitle(title);
        return ApiResponse.success("搜索文章成功", posts);
    }
    
    @Operation(summary = "健康检查", description = "检查博客API服务状态")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "服务正常运行")
    })
    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("Blog API is running!", "服务正常运行");
    }
} 
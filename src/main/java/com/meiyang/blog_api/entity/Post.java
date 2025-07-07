package com.meiyang.blog_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "博客文章实体")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "文章ID", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "文章标题", example = "我的第一篇博客")
    private String title;
    
    @Column(columnDefinition = "TEXT")
    @Schema(description = "文章内容", example = "这是文章的内容...")
    private String content;
    
    @Column(nullable = false)
    @Schema(description = "作者", example = "张三")
    private String author;
    
    @Column(name = "created_at")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 
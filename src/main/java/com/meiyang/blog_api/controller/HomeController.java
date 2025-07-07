package com.meiyang.blog_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meiyang.blog_api.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "系统管理", description = "系统基础接口")
public class HomeController {
    
    @Operation(summary = "首页", description = "获取API基本信息和所有可用接口")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取API信息")
    })
    @GetMapping("/")
    public ApiResponse<Map<String, Object>> home() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Welcome to Blog API!");
        data.put("version", "1.0.0");
        data.put("status", "running");
        data.put("endpoints", new String[]{
            "GET /api/posts - 获取所有文章",
            "GET /api/posts/{id} - 获取指定文章",
            "POST /api/posts - 创建新文章",
            "PUT /api/posts/{id} - 更新文章",
            "DELETE /api/posts/{id} - 删除文章",
            "GET /api/posts/author/{author} - 根据作者获取文章",
            "GET /api/posts/search?title=关键词 - 搜索文章",
            "GET /h2-console - H2数据库控制台",
            "GET /swagger-ui.html - API文档界面"
        });
        return ApiResponse.success("获取API信息成功", data);
    }
    
    @Operation(summary = "健康检查", description = "检查系统运行状态")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "系统正常运行")
    })
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        Map<String, String> data = new HashMap<>();
        data.put("status", "UP");
        data.put("message", "Blog API is healthy!");
        return ApiResponse.success("系统运行正常", data);
    }
} 
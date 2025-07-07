package com.meiyang.blog_api.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "统一API响应格式")
public class ApiResponse<T> {
    
    @Schema(description = "响应状态码", example = "200")
    private Integer code;
    
    @Schema(description = "响应消息", example = "操作成功")
    private String message;
    
    @Schema(description = "响应数据")
    private T data;
    
    @Schema(description = "响应时间")
    private LocalDateTime timestamp;
    
    @Schema(description = "请求路径", example = "/api/posts")
    private String path;
    
    // 成功响应 - 无数据
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "操作成功", null, LocalDateTime.now(), null);
    }
    
    // 成功响应 - 有数据
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data, LocalDateTime.now(), null);
    }
    
    // 成功响应 - 自定义消息
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data, LocalDateTime.now(), null);
    }
    
    // 失败响应
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now(), null);
    }
    
    // 失败响应 - 400
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message, null, LocalDateTime.now(), null);
    }
    
    // 失败响应 - 404
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null, LocalDateTime.now(), null);
    }
    
    // 失败响应 - 500
    public static <T> ApiResponse<T> serverError(String message) {
        return new ApiResponse<>(500, message, null, LocalDateTime.now(), null);
    }
    
    // 设置请求路径
    public ApiResponse<T> path(String path) {
        this.path = path;
        return this;
    }
} 
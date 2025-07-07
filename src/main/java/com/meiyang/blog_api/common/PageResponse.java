package com.meiyang.blog_api.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页响应格式")
public class PageResponse<T> {
    
    @Schema(description = "数据列表")
    private List<T> content;
    
    @Schema(description = "当前页码", example = "1")
    private Integer page;
    
    @Schema(description = "每页大小", example = "10")
    private Integer size;
    
    @Schema(description = "总记录数", example = "100")
    private Long total;
    
    @Schema(description = "总页数", example = "10")
    private Integer totalPages;
    
    @Schema(description = "是否有下一页", example = "true")
    private Boolean hasNext;
    
    @Schema(description = "是否有上一页", example = "false")
    private Boolean hasPrevious;
    
    // 创建分页响应
    public static <T> PageResponse<T> of(List<T> content, Integer page, Integer size, Long total) {
        int totalPages = (int) Math.ceil((double) total / size);
        return new PageResponse<>(
            content,
            page,
            size,
            total,
            totalPages,
            page < totalPages,
            page > 1
        );
    }
} 
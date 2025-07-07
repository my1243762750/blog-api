# 博客API项目

这是一个基于Spring Boot的博客系统API，提供文章的增删改查功能。

## 🚀 快速开始

### 启动项目
```bash
mvn spring-boot:run
```

### 访问地址
- **API文档**: http://localhost:8080/swagger-ui.html
- **首页**: http://localhost:8080/
- **健康检查**: http://localhost:8080/health
- **H2数据库控制台**: http://localhost:8080/h2-console

## 📚 API文档

### 在线文档
启动项目后，访问 **http://localhost:8080/swagger-ui.html** 查看完整的API文档。

### 主要接口

#### 1. 文章管理接口 (`/api/posts`)

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/posts` | 获取所有文章 |
| GET | `/api/posts/{id}` | 获取指定文章 |
| POST | `/api/posts` | 创建新文章 |
| PUT | `/api/posts/{id}` | 更新文章 |
| DELETE | `/api/posts/{id}` | 删除文章 |
| GET | `/api/posts/author/{author}` | 根据作者获取文章 |
| GET | `/api/posts/search?title=关键词` | 搜索文章 |

#### 2. 系统接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/` | 首页信息 |
| GET | `/health` | 健康检查 |

## 🏗️ 项目结构

```
src/main/java/com/meiyang/blog_api/
├── BlogApiApplication.java          # 启动类
├── config/
│   └── OpenApiConfig.java          # Swagger配置
├── controller/                      # 控制层
│   ├── HomeController.java         # 首页控制器
│   └── PostController.java         # 文章控制器
├── service/                        # 服务层
│   └── PostService.java           # 文章业务逻辑
├── repository/                     # 数据访问层
│   └── PostRepository.java        # 文章数据访问
└── entity/                        # 实体层
    └── Post.java                  # 文章实体
```

## 📋 分层架构说明

### 1. **Controller层** (控制层)
- 处理HTTP请求和响应
- 接收客户端参数
- 调用Service层处理业务逻辑
- 返回处理结果

### 2. **Service层** (服务层)
- 处理业务逻辑
- 调用Repository层进行数据操作
- 提供事务管理

### 3. **Repository层** (数据访问层)
- 与数据库交互
- 提供数据增删改查方法
- 基于Spring Data JPA

### 4. **Entity层** (实体层)
- 定义数据模型
- 映射数据库表结构
- 包含数据验证规则

## 🛠️ 技术栈

- **Spring Boot 3.5.3** - 主框架
- **Spring Data JPA** - 数据访问
- **H2 Database** - 内存数据库
- **Lombok** - 代码简化
- **Swagger/OpenAPI** - API文档

## 📝 使用示例

### 创建文章
```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "我的第一篇博客",
    "content": "这是文章的内容...",
    "author": "张三"
  }'
```

### 获取所有文章
```bash
curl http://localhost:8080/api/posts
```

### 获取指定文章
```bash
curl http://localhost:8080/api/posts/1
```

### 更新文章
```bash
curl -X PUT http://localhost:8080/api/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "更新后的标题",
    "content": "更新后的内容",
    "author": "张三"
  }'
```

### 删除文章
```bash
curl -X DELETE http://localhost:8080/api/posts/1
```

### 搜索文章
```bash
curl "http://localhost:8080/api/posts/search?title=Spring"
```

### 根据作者查询
```bash
curl http://localhost:8080/api/posts/author/张三
```

## 🔧 配置说明

### 数据库配置
- **类型**: H2内存数据库
- **URL**: jdbc:h2:mem:blogdb
- **用户名**: sa
- **密码**: (空)

### API文档配置
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API文档**: http://localhost:8080/api-docs

## 📊 数据模型

### Post (文章实体)
```json
{
  "id": 1,
  "title": "文章标题",
  "content": "文章内容",
  "author": "作者",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

## 🎯 开发建议

1. **查看API文档**: 启动项目后访问Swagger UI查看完整接口
2. **测试接口**: 使用curl或Postman测试各个接口
3. **数据库管理**: 访问H2控制台查看数据
4. **日志查看**: 查看控制台日志了解请求处理过程

## 📞 联系方式

如有问题，请联系开发者或查看API文档获取更多信息。
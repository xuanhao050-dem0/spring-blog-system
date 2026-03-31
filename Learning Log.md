# 学习日志 (Learning Log)

> 记录在学习 Spring Blog Demo 项目过程中的知识点和踩坑经历。

---

#### 📅 问题 01 | 日期：2026-03-31 | 功能：统一响应包装类 Result
> **涉及代码**：`src/main/java/com/bit/springblogdemo/pojo/response/Result.java` 第 12-18 行

##### A. 为什么这么写？ (The "Why")

- **解决什么问题**：前端每次调用接口，返回的数据格式可能不一样，难以处理。统一响应类让所有接口返回格式一致，前端只需要判断 `code` 是 200 还是 -1 就能知道成功还是失败。
- **数据流向图解**：
  ```
  前端请求 → Controller → Service → Mapper → 数据库
                    ↓
             Result.success(data)  ← 包装返回数据
                    ↓
             返回 JSON → 前端
  ```

##### B. 核心知识点 (Key Concepts)

- **框架特性**：`@Data` 是 Lombok 注解，自动生成 getter/setter 方法，不用手动写。
- **泛型 `<T>`**：让 `Result` 可以装任何类型的数据——用户对象、文章列表、分页信息都可以。
- **避坑指南**：第 23 行有个 bug，`getErrMsg(errMsg)` 应该是 `setErrMsg(errMsg)`，新手容易抄错！

##### C. 架构初探 (Architecture 101)

- **目录规范**：放在 `pojo/response` 包下，表示"响应数据对象"，和请求对象 `pojo/request` 对应。
- **举一反三**：这个模式还可以用于：
  - 分页结果 `PageResult<T>`
  - 文件上传响应
  - 树形结构数据返回

##### D. 职场小贴士 (Professional Tip)

- **企业习惯**：真实项目还会加上：
  - `timestamp` 字段（返回响应时间，方便排查问题）
  - 全局异常处理器 `@ControllerAdvice`（统一捕获并包装错误）
  - 统一响应工具类可添加静态导入：`import static com.xxx.Result.*;`

---

#### 📅 问题 02 | 日期：2026-03-31 | 功能：@SneakyThrows 与 @Autowired 标红问题排查
> **涉及代码**：`src/main/java/com/bit/springblogdemo/common/advice/ResponseAdvice.java` 第 13 行

##### A. 为什么这么写？ (The "Why")

- **解决什么问题**：`@SneakyThrows` 本身不会影响 `@Autowired`，但它会"掩盖"异常（把受检异常变成运行时异常）。真正导致 `@Autowired` 标红的原因是类没有被 Spring 管理。
- **数据流向图解**：
  ```
  用户请求 → Controller → 返回数据
                         ↓
              ResponseAdvice.beforeBodyWrite()  ← 拦截所有响应
                         ↓
              自动包装成 Result 统一格式 → 前端
  ```

##### B. 核心知识点 (Key Concepts)

- **`@Component`**：告诉 Spring "这个类需要你来管理"，Spring 会自动创建实例并注入依赖。少了它，`@Autowired` 就找不到可以注入的 Bean。
- **`@SneakyThrows`**：Lombok 注解，把方法内的受检异常（如 `IOException`）偷偷抛出，不用 `try-catch` 或 `throws`。
- **避坑指南**：
  - 刚创建新类时，**一定要记得加 `@Component`**（或 `@Service`、`@Repository`）
  - 工具类、配置类、拦截器都需要被 Spring 管理才能用 `@Autowired`

##### C. 架构初探 (Architecture 101)

- **目录规范**：放在 `common/advice` 包下，表示"通用切面/增强处理"，这是 AOP 的典型应用。
- **举一反三**：这类"拦截所有响应"的模式还可以用于：
  - 统一日志记录（记录每次接口调用）
  - 敏感数据脱敏（隐藏手机号、身份证号）

##### D. 职场小贴士 (Professional Tip)

- **企业习惯**：
  - `@Component` 还可以换成更具体的注解：`@Service`（服务层）、`@Repository`（数据层）
  - `ObjectMapper` 也可以通过构造函数注入：`public ResponseAdvice(ObjectMapper objectMapper) { this.objectMapper = objectMapper; }`，这样更符合 Spring 推荐的做法（构造器注入，更容易做单元测试）

---


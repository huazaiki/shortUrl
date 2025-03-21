# ShortUrl: 基于Java的短链接生成器

## Require:
- Java 17
- SpringBoot 3.x
- MySQL 8.0.39及以上
- Maven 3.x

## Quick Start
1. 将代码克隆到本地
    ```text
    git clone https://github.com/huazaiki/shortUrl.git
    ```
2. 配置数据库
3. 打包项目
    ```text
    maven install && maven package 
    ```

## TODOs

- [ ] 验证输入网址的格式
- [ ] 改进随机生成字符串算法
- [ ] 添加日志系统
- [ ] [wrk](https://github.com/wg/wrk)服务器基准测试
- [ ] 添加测试用例
- [ ] 后端
  - [ ] 调整短路径生成算法
  - [ ] Swagger
  - [ ] 自定义短网址
  - [ ] 计算每个 URL 解析时间 (高并发情况)
  - [ ] 添加sqlite数据库支持
- [ ] 前端
  - [ ] 编写一个简单界面

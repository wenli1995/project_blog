一、技术栈
前端：HTML+CSS+JavaScript
后端：JSP/Servlet,Java
数据库：MySQL

二、软件版本
JDK：1.8.0_231
Tomcat:Apache Tomcat v7.0
数据库驱动：mysql-connector-java-8.0.12.jar
MySQL数据库：Server version: 8.0.12
(JDK版本，数据库驱动，以及数据库本身的版本要兼容）
MySQL5升级到MySQL8：https://blog.csdn.net/TD520314/article/details/80461545

三、项目导入
Blog文件夹导入Eclipse工作空间
参考博文：https://blog.csdn.net/clay_zhang/article/details/50924941

四、项目运行
1、数据库：参看db_blog.sql文件
2、后台：Eclipse IDE的Servers页签右键新建一个Tomcat Server，添加”Blog“项目后，启动Tomcat Server
3、前端：浏览器输入http://localhost:8080/Blog/ 进行访问

五、功能介绍
（一）首页
1、默认按时间显示全部博客 
2、显示阅读排行榜：按阅读量从高到低进行排序 
3、显示推荐排行榜：按推荐数从高到低进行排序

（二）用户登录，注册，退出功能

（三）博客管理
1、按时间顺序显示我的所有博客
2、按时间查询博客
3、阅读排行的博客列表，评论排行的博客列表，推荐排行的评论列表
4、博客分类汇总
5、查看博客详情
6、新增博客发布或保存草稿，编辑博客，删除博客

（四）评论管理
1、博客详情页显示博客主题以及评论
2、新增博客评论

（五）用户管理功能
1、用户信息显示
2、用户信息修改
/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.1.49-community : Database - db_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_blog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blogId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `blogType` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1是已发布，0是未发布',
  PRIMARY KEY (`blogId`),
  KEY `userId` (`userId`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`blogId`,`userId`,`userName`,`title`,`content`,`blogType`,`createTime`,`views`,`likes`,`status`) values (1,3,'Susy','数据库系统概念-事务','事务ACID属性','数据库','2014-12-13 15:17:07',12,4,1),(2,3,'Susy','操作系统之虚拟内存','虚拟内存是计算机系统内存管理的一种技术。它使得应用程序认为它拥有连续的可用的内存（一个连续完整的地址空间），而实际上，它通常是被分隔成多个物理内存碎片，还有部分暂时存储在外部磁盘存储器上，在需要时进行数据交换。目前，大多数操作系统都使用了虚拟内存，如Windows家族的“虚拟内存”；Linux的“交换空间”等。','操作系统','2014-09-17 15:17:07',102,1,1),(3,3,'Susy','计算机网络核心思想','计算机网络也称计算机通信网。关于计算机网络的最简单定义是：一些相互连接的、以共享资源为目的的、自治的计算机的集合。若按此定义，则早期的面向终端的网络都不能算是计算机网络，而只能称为联机系统（因为那时的许多终端不能算是自治的计算机）。但随着硬件价格的下降，许多终端都具有一定的智能，因而“终端”和“自治的计算机”逐渐失去了严格的界限。若用微型计算机作为终端使用，按上述定义，则早期的那种面向终端的网络也可称为计算机网络','计算机网络','2014-09-13 16:20:23',23,10,1),(4,1,'Cindy','数据库系统概念-数据库范式','目前关系数据库有六种范式：第一范式（1NF）、第二范式（2NF）、第三范式（3NF）、巴斯-科德范式（BCNF）、第四范式(4NF）和第五范式（5NF，又称完美范式）。满足最低要求的范式是第一范式（1NF）。在第一范式的基础上进一步满足更多规范要求的称为第二范式（2NF），其余范式以次类推。一般说来，数据库只需满足第三范式(3NF）就行了。','数据库','2017-09-20 11:27:07',1,12,1),(5,3,'Susy','PHP进程管理','进程是正在运行的程序实体，并且包括这个运行的程序中占据的所有系统资源，比如说CPU（寄存器），IO,内存，网络资源等。很多人在回答进程的概念的时候，往往只会说它是一个运行的实体，而会忽略掉进程所占据的资源。比如说，同样一个程序，同一时刻被两次运行了，那么他们就是两个独立的进程。linux下查看系统进程的命令是ps','数据库','2016-05-13 12:17:07',15,11,1),(6,3,'Susy','移动端兼容和适配问题','兼容性（compatibility）是指硬件之间、软件之间或是软硬件组合系统之间的相互协调工作的程度。兼容的概念比较广，相对于硬件来说，几种不同的电脑部件，如CPU、主板、显示卡等，如果在工作时能够相互配合、稳定地工作，就说它们之间的兼容性比较好，反之就是兼容性不好。','移动端','2016-09-13 09:12:07',6,15,1),(7,2,'Alice','设计模式与设计原则简介（一）','设计模式是一套被反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。毫无疑问，设计模式于己于他人于系统都是多赢的，设计模式使代码编制真正工程化，设计模式是软件工程的基石，如同大厦的一块块砖石一样。项目中合理地运用设计模式可以完美地解决很多问题，每种模式在现实中都有相应的原理来与之对应，每种模式都描述了一个在我们周围不断重复发生的问题，以及该问题的核心解决方案，这也是设计模式能被广泛应用的原因','设计模式','2018-07-13 22:17:07',4,0,1),(8,2,'Alice','Supervised learning demo','监督学习案例 规范 + 假设函数: 使用h(hypothesis, 假设)表示 + 输入(input value): x + 输出(output value): y + 参数(Parameters): $	heta$ 房价的Size和Price的预测 + 建立一个线性模型','机器学习','2018-03-23 23:16:07',9,4,1),(9,1,'Cindy','WebGL树形结构的模型渲染流程','在较低档计算机上编写的程序，可以在同一系列的较高档计算机上运行，或者在某一平台的较低版本环境中编写的程序可以在较高版本的环境中运行，都称为向上兼容，前者是硬件兼容，而后者是软件兼容，例如，基于Intel386的PC兼容机上所有的软件也可以运行在486或更高的机型上。向上兼容具有非常重要的意义，一些大型软件的开发，工作量极大，如这些软件都能做到兼容，则无需在其它机器上重新开发，就可节省庞大的人力和物力','编程语言','2017-10-16 20:17:07',11,2,1),(10,1,'Cindy','java编程思想','Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程','编程语言','2017-03-13 16:17:27',23,7,1),(11,1,'Cindy','鸟哥的Linux私房菜笔记','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2017-04-13 15:17:04',22,22,1),(12,2,'Alice','鸟哥的Linux私房菜笔记','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-10 21:54:04',2,22,1),(13,1,'Cindy','免费使用和自由传播','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-09 15:17:04',22,12,1),(14,1,'Cindy','基于Intel386的PC兼容机上所有的软件','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 15:27:04',76,34,1),(15,2,'Alice','基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-10 18:17:04',0,2,1),(16,1,'Cindy','设计模式与设计原则简介（一）','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 21:17:04',12,6,1),(17,2,'Alice','Java是一门面向对象编程语言','这是一个面试经常被问到的问题，很多问题都可以转化为这个模型。\n什么是生产者与消费者问题？举个例子，我们去吃自助餐，在自助餐的一个公共区域放着各种食物，消费者需要就自行挑选，当食物被挑没的时候，大家就等待，等候厨师做出更多再放到公共区域内供大家挑选；当公共区域食物达到一定数量，不能再存放的时候，此时没有消费者挑选，厨师此时等待，等公共区域有地方再存放食物时，再开始生产。这就是一个生产者与消费者问题。\n根据这个例子，我们可以模拟一下场景，我们从这个例子中，显然看出我们需要制造一个公共区域，而且这个公共区域是有容量限制的，需要模拟各种食物，同时还需要模拟几个厨师也就是生产者，最后再模拟几个消费者。\n首先呢，我们创建一个产品Product类，这个类就代表食物的模板，厨师们就生产这种类型的食物，类里面定义食物的ID和name这两个属性，代码如下','数据库','2018-11-12 23:17:04',17,57,1),(18,2,'Alice','同样一个程序，同一时刻被两次运行了，那么他们就是两个独立的进程','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-11 12:17:04',5,11,1),(19,2,'Alice','代码改变世界','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 16:17:04',2,0,1),(20,1,'Cindy','一个WEB服务器的实现','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-11 15:17:04',45,1,1),(21,3,'Susy','哈哈哈哈加油吧','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-10 09:17:04',7,2,1),(22,1,'Cindy','多啦A梦的百宝箱','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-11 21:17:04',0,25,1),(23,2,'Alice','柯南没长大，童年依然在','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 16:17:04',19,2,1),(24,2,'Alice','java三大设计模式','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-11 22:17:04',8,34,1),(25,3,'Susy','虚拟机的原理与应用','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 18:17:04',108,2,1),(26,1,'Cindy','鸟哥的Linux私房菜笔记第二版','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-11 15:17:04',4,2,1),(27,1,'Cindy','现代操作系统基本原理','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。','数据库','2018-11-12 19:17:04',2,56,1),(28,2,'Alice','RobotFramWork创建系统关键字','<p>RobotFramwWordRobot framework 本质上是基于 Python 语言开发的一个框架，它提供了一套独立的语法。它本身只提供基础的一些功能。比如，它自带的&nbsp;Builtin&nbsp;库中提供的关键字告诉你如何定义变量、数组和字典，打印信息，分支语句和循环等。以及框架本身所提供的 &ldquo;自动化&rdquo; 功能，如何组织用例，生成测试报告</p>\r\n','自动化测试','2018-11-24 12:58:27',3,2,1),(29,2,'Alice','python入门','<p>Robot Framework 所支持的库主要分&nbsp;标准库&nbsp;、&nbsp;扩展库&nbsp;和 其它 。 标准库提供基本功能，扩展库提供特定领域的操作。</p>\r\n\r\n<p>因为 Robot Framework 所支持的测试库非常多，这里例一些常用的</p>\r\n','python','2018-11-24 13:03:53',0,0,0),(32,2,'Alice','2','<p>2222</p>\r\n','22','2018-11-24 16:14:47',0,0,0),(43,2,'Alice','tt111','<p>ttt</p>\r\n','ttt','2018-11-24 20:06:12',0,0,0),(44,2,'Alice','gg','<p>ggg</p>\r\n','ggg','2018-11-24 20:10:13',5,2,1),(45,2,'Alice','42255','<p>444</p>\r\n','4444','2018-11-24 20:10:22',0,0,0),(46,2,'Alice','博客园简介','<p>一个IT技术人员想为IT技术人员们提供一个纯净的技术交流空间，博客园很长时间只有一个不能再简单的博客，有近四年，博客员仅靠一个人几年工作的积蓄在维持，互联网浪潮的此起彼伏，&quot;博客&quot;从耀眼的明星成为平民，这些似乎都与博客园无关，博客园<a href=\"https://baike.so.com/doc/2472275-2613026.html\" target=\"_blank\">一步一个脚印</a>地走着自己的路，傻傻地对每个用户注册进行人工审批、对首页内容宁缺毋滥、对不合适的广告拒之门外，傻傻地对用户体验关怀备至，对盈利模式冷若冰霜。这样一个不起眼的地方，却吸引了很多IT技术精英，把这里当作自己的网上家园，每天在这里分享着精彩的原创内容，也许他们看重的不是华丽的外表、诱人的虚名，而是纯净、专注、对技术人员的理解。博客园来到了<a href=\"https://baike.so.com/doc/1253613-1325811.html\" target=\"_blank\">上海</a>，有了自己的团队，注册用户4万多，每天有15万以上的用户访问，除了博客，有了互动交流的小组，有了你问我答的博问，有了收藏精彩内容的网摘，有了搜索站内内容的找找看，有了随时记录思想火花的闪存，有了随时了解业内动态的新闻频道，有了知识库，有了期刊，有了&hellip;&hellip;</p>\r\n\r\n<p>博客园的用户中成长出了一批又一批专家，在IT行业中大展身手!</p>\r\n\r\n<p>博客园在行业中的影响力越来越大!</p>\r\n\r\n<p>博客园正朝着高品质的IT媒体与社区方向发展&hellip;&hellip;</p>\r\n\r\n<p>这些文字不能清楚地描述出真正的博客园，我们知道:真正的博客园体现于我们每天为用户提供的服务!</p>\r\n\r\n<p>2004年1月 博客园在江苏扬州诞生。</p>\r\n\r\n<p>2006年9月 博客园到北京发展。</p>\r\n\r\n<p>2006年9月 与博文视点合作系列图书。</p>\r\n\r\n<p>2007年5月 博客园到广州发展。</p>\r\n\r\n<p>2007年9月 博客园来到<a href=\"https://baike.so.com/doc/1253613-1325811.html\" target=\"_blank\">上海</a>发展。</p>\r\n\r\n<p>2007年11月 博客园社区上线。</p>\r\n\r\n<p>2007年12月 博客园首次招聘。</p>\r\n\r\n<p>2008年3月 博客园团队成立。</p>\r\n\r\n<p>2008年6月 博客园<a href=\"https://baike.so.com/doc/782738-828179.html\" target=\"_blank\">新闻</a>频道上线。</p>\r\n\r\n<p>2008年6月 发布博客园T恤。</p>\r\n\r\n<p>2008年8月 博客园找找看上线。</p>\r\n\r\n<p>2008年9月 博客园在地铁站的宣传。</p>\r\n\r\n<p>2008年9月 博客园网摘上线</p>\r\n','其他','2018-11-26 22:22:22',55,6,1),(47,33,'HHY','第一条博客','<p>这是发表的第一条博客</p>\r\n','测试','2018-11-28 22:09:32',40,15,1);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `blogId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `reviewTime` datetime DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `userId` (`userId`),
  KEY `blogId` (`blogId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`blogId`) REFERENCES `blog` (`blogId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`commentId`,`userId`,`blogId`,`content`,`reviewTime`) values (1,1,17,'感谢楼主分享','2014-09-17 15:17:07'),(2,3,7,'学习了','2017-09-12 15:17:07'),(3,2,17,'这里的表述有误','2018-09-12 15:17:07'),(4,1,15,'d','2018-11-14 00:33:15'),(5,1,15,'这里的表述有误','2018-10-15 00:33:23'),(6,1,15,'这里的表述有误','2018-11-12 00:33:34'),(7,2,8,'写的真好','2018-10-15 00:33:38'),(8,2,8,'写的真好','2018-10-15 00:34:02'),(9,3,12,'写的真好','2018-08-20 00:34:11'),(10,3,19,'写的真好','2018-07-09 00:34:20'),(11,3,19,'写的真好','2018-05-21 00:34:28'),(12,3,19,'写的真好','2018-09-17 00:34:37'),(13,3,17,'666666','2018-05-14 00:34:44'),(14,2,17,'第十行那里是不是表述错了，应该是机械工业出版的书','2018-07-09 00:34:51'),(15,2,18,'写的真好','2018-11-05 00:34:59'),(16,1,23,'写的真好','2018-11-04 00:35:03'),(17,1,22,'写的真好','2018-11-04 00:35:10'),(18,2,17,'333333','2018-11-25 16:44:53'),(19,2,46,'测试评论数','2018-11-28 21:08:09'),(20,33,47,'成功了','2018-11-30 01:21:07');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `motto` varchar(30) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`password`,`motto`,`points`,`registerDate`) values (1,'Cindy','123456','只为成功找方法，不为失败找借口',25,'2017-06-12'),(2,'Alice','123123','任何时候都一定要相信自己',5,'2018-03-15'),(3,'Susy','111111','仰望星空，脚踏实地',2,'2014-08-15'),(4,'雪山飞狐','123456','只为成功找方法，不为失败找借口',102,'2017-06-12'),(5,'大漠苍鹰','123456','只为成功找方法，不为失败找借口',15,'2017-06-12'),(6,'飞','123456','只为成功找方法，不为失败找借口',52,'2017-06-12'),(7,'Wendy','123456','只为成功找方法，不为失败找借口',52,'2017-06-12'),(8,'逍遥游','123456','只为成功找方法，不为失败找借口',33,'2017-06-12'),(9,'micheal','123456','只为成功找方法，不为失败找借口',22,'2017-06-12'),(10,'草莓先生','123456','只为成功找方法，不为失败找借口',12,'2017-06-12'),(11,'他先生','123456','只为成功找方法，不为失败找借口',34,'2017-06-12'),(12,'六便士','123456','只为成功找方法，不为失败找借口',12,'2017-06-12'),(13,'沧海一声笑','123456','只为成功找方法，不为失败找借口',8,'2017-06-12'),(14,'涛涛两岸情','123456','只为成功找方法，不为失败找借口',3,'2017-06-12'),(15,'qwqw','123456','只为成功找方法，不为失败找借口',0,'2017-06-12'),(16,'tftf','123456','只为成功找方法，不为失败找借口',12,'2017-06-12'),(17,'他山之石','123456','只为成功找方法，不为失败找借口',43,'2017-06-12'),(18,'坚持就是胜利','123456','只为成功找方法，不为失败找借口',2,'2017-06-12'),(19,'小城故事','123456','只为成功找方法，不为失败找借口',111,'2017-06-12'),(20,'三叶草','123456','只为成功找方法，不为失败找借口',2,'2017-06-12'),(21,'云飞','123456','只为成功找方法，不为失败找借口',22,'2017-06-12'),(22,'多啦B梦','123456','只为成功找方法，不为失败找借口',16,'2017-06-12'),(23,'大熊本熊','123456','只为成功找方法，不为失败找借口',34,'2017-06-12'),(24,'书架','123456','只为成功找方法，不为失败找借口',43,'2017-06-12'),(25,'王嘉尔','123456','只为成功找方法，不为失败找借口',73,'2017-06-12'),(26,'傲视群雄','123456','只为成功找方法，不为失败找借口',55,'2017-06-12'),(27,'奥德加','123456','只为成功找方法，不为失败找借口',1,'2017-06-12'),(28,'qwdx','123456','只为成功找方法，不为失败找借口',6,'2017-06-12'),(29,'瑟吉欧','123456','只为成功找方法，不为失败找借口',7,'2017-06-12'),(30,'这很厉害','123456','只为成功找方法，不为失败找借口',24,'2017-06-12'),(31,'Moli','123456','',0,'2018-11-28'),(32,'Molia','123','',0,'2018-11-28'),(33,'HHY','123','',0,'2018-11-28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

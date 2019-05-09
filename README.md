# PaperSystemBackend
### 配置mysql时可能存在的问题
  - 如果安装过程中出现配置windows服务的选项，务必选择并记住服务名称
  - 如果安装64bit的mysql则需要先安装64bit的python，32bit类推
  - 如果使用第三方数据库链接工具无法链接mysql8，则需要修改加密方式，参考<https://www.jianshu.com/p/a8e2e48fb5b3>
  - 如果出现BigInteger无法转化为long的异常，则检查自己的mysql版本与mysql-connector-java这个jar的版本是否一致，不一致则下载相应版本的jar包
### 导入项目
  - 执行指令git clone https://github.com/XHHuiL/PaperSystemBackend.git 将代码克隆到本地，或者直接下载zip压缩包
  - 如果lib下的jar包没有添加依赖，那么依次在这些jar包上 右击 > Add as library
  - 如果src不是sources root，那么 右击 > Mark Directory as > sources root
  - 如果test不是test root， 那么 右击 > Mark Directory as > test sources root
  - 如果out不是excluded，那么 右击 > Mark Directory as > excluded
### 代码注意事项
  - 在src/util目录下的JdbcUtil.java的构造函数中，注意mysql5 driver path is "com.mysql.jdbc.Driver"，mysql8 driver path is "com.mysql.cj.jdbc.Driver"
  - 在test/dao/impl目录下的ChapterDaoImplTest.java中，注意setUp()中的语句runtime.exec("net start mysql8").waitFor()。
  这条语句执行系统调用，会开启windows系统中的mysql8服务，waitFor()表示只有当系统调用完成后才继续执行。
  - 启动mysql8服务、关闭mysql8服务在设计某些测试用例时有用，具体参考test/dao/impl下的ChapterDaoImplTest.java
### 辅助工具
  - Navicat Premium：数据库链接工具，可以执行sql文件导入数据库

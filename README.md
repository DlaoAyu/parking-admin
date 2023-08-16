# parking-admin

#### 介绍
智慧车位管理系统，后端

# 项目前端地址
1. Github地址 > https://github.com/DlaoAyu/parking-vue.git
2. Gitee地址 > https://gitee.com/laoayu/parking-vue.git
#### 软件架构
软件架构说明 
    使用Spring Boot + Mybatis Plus的后端


#### 安装教程

1.  推荐使用idea导入该项目
2.  引入对应的maven依赖
3.  用数据可视化工具导入resources下的parking.sql数据库文件
4.  配置好数据库和密码
5.  找到src\main\java\com\laoayu\parking\ParkingApplication.java
6.  点击绿色小箭头运行该项目

#### 使用说明

1.  推荐使用 JDK 1.8
2.  推荐使用 Apache Maven 3.6.3
3.  数据库推荐使用 MySQL 8.0.20

### 车牌识别部分使用说明

1.  注册百度智能云账号 网址：https://cloud.baidu.com/?from=console
2.  领取免费车牌识别API并创建自己的APP
3.  根据百度智能云官网教程获取APP_ID, API_KEY, SECRET_KEY
4.  将src\main\java\com\laoayu\parking\common\utils\ApiOcrUtil.java下的以上三项改为自己的

### 第三方登录验证
暂时实现gitee验证，暂时未真正实现第三方登录功能
期待大佬能帮忙助力实现该功能


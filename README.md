# Backend-2

## 尝试cloud-alibaba
- java 1.8
- 其他版本参考父pom

## 一些软件版本
- nacos 1.1.4 https://github.com/alibaba/nacos/releases/tag/1.1.4
- cloud alibaba 2.1.0
- sentinel 1.7.0 https://github.com/alibaba/Sentinel/releases/tag/1.7.0

## tips
- java -jar sentinel-dashboard-1.7.0.jar
- devtools热部署，前提，file->settings->compiler adbc都勾选
- docker端口、文件夹映射，以mysql为例`docker run -p 3306:3306 --name mysql -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7`
- docker进入某个容器，以mysql为例`docker exec -it mysql /bin/bash`
- docker的`mysql:5.7`的密码是`!@#$abcd`
- nacos配置的时间顺序：bootstrap->application->本地依据环境的配置->nacos中心的配置，后面的配置可以覆盖前面的配置

`docker run -p 8848:8848 --name nacos -v /mydata/nacos/:/home/nacos --env MODE=standalone -d nacos/nacos-server:1.1.4`
## todo
- nacos集群没有做,目前的MODE=standalone
- zipkin还没使用
- docker容器会莫名关闭，目前看来原因是内存不够，
- 把所有服务都部署，测试一下，尝试nginx代理
- nginx代理网关，看下智能问答

## done
- sentinel服务降级、nacos注册、配置、服务总线在cloud-common
- 配置中心在8002/config/info
- gateway动态路由
- consumer使用openfeign服务调用

## 部署
- 开启gateway网关
- 开启provider和consumer

## Jenkins

- 容器运行时挂载maven、jdk`docker run -d -p 8080:8080 -v /mydata/jenkins/data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /opt/maven/apache-maven-3.6.3:/usr/local/maven -v /usr/lib/java8/jdk1.8.0_261:/usr/local/jdk -v /etc/localtime:/etc/localtime --name jenkins jenkinsci/blueocean`
- 容器运行jenkins，遇到的坑
    - maven要另外挂载
    - jdk忘记挂载就另外安装
    - 使其能运行docker
    - mvn找不到，除了要容器内配置，jenkins配置，jenkins控制台的环境变量也要写
    - 阿里云用户名有中文，jenkins系统编码不是utf8，导致无法登录
    
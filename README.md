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

`docker run -p 8848:8848 --name nacos -v /mydata/nacos/:/home/nacos --env MODE=standalone -d nacos/nacos-server:1.1.4`
## todo
- 父工程pom不知道一些依赖、build插件、profiles怎么玩
- nacos集群没有做,目前的MODE=standalone
- 服务器搞docker来装需要的服务
- zipkin要开服务

## done
- sentinel服务降级、nacos注册、配置、服务总线在cloud-common
- 配置中心在8002/config/info
- gateway动态路由
- consumer使用openfeign服务调用

## 部署
- 开启gateway网关
- 开启provider和consumer

## 服务的docker

- `docker build -f Dockerfile -t docker.io/group2/gateway:0.0.1 .`
- `docker run -d --name gateway -p 9001:9001 group2/gateway:0.0.1`
- 381-10-54

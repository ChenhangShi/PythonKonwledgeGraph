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
- 其他容器挂载的目录一定别忘了，


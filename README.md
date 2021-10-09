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

`docker run -d -p 8848:8848  --env MODE=standalone --name nacos registry.cn-hangzhou.aliyuncs.com/group2/nacos_ypx`
`docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard:1.7.0`
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

## Jenkins

- `docker run -d -p 8080:8080 -v /mydata/nginx:/var/nginx_home -v /mydata/jenkins/data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /usr/share/maven/apache-maven-3.6.3:/usr/local/maven -v /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.302.b08-0.el7_9.x86_64:/usr/local/jdk -v /usr/share/nodejs/node-v14.18.0-linux-x64:/usr/local/nodejs -v /etc/localtime:/etc/localtime --name jenkins registry.cn-hangzhou.aliyuncs.com/group2/jenkins_ypx`
- 除此以外，容器内的/root/.bashrc环境变量要配置
- 容器运行jenkins，遇到的坑
    - maven等要另外挂载
    - jdk忘记挂载就另外安装
    - 使其能运行docker，挂载见上面的命令
    - mvn找不到
      - 除了要容器内配置（环境变量`/etc/profile` `/root/.bashrc`）
      - jenkins配置（插件）
      - jenkins控制台的环境变量也要写
    - 阿里云用户名有中文，jenkins系统编码不是utf8，导致无法登录
    - 外部的文件是root权限，其他用户想要操作，就修改文件的权限

## Nginx

- `docker run --name nginx -d -p 80:80 -v /mydata/nginx/conf:/etc/nginx -v /mydata/nginx/html:/usr/share/nginx/html -v /mydata/nginx/logs:/var/log/nginx registry.cn-hangzhou.aliyuncs.com/group2/nginx_ypx:v1`

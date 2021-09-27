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

## todo
- [ ] 父工程pom不知道一些依赖、build插件、profiles怎么玩
- [ ] nacos集群没有做

## done
- sentinel服务降级、nacos注册、配置、服务总线在cloud-common
- 配置中心在8002/config/info
- gateway动态路由
- consumer使用openfeign服务调用

# SpringCloud 微服务简单使用

## 服务介绍

所有服务基于SpringBoot`1.4.4.RELEASE`和SpringCloud`Camden.RELEASE`

### teclan-springcloud-eureka 

微服务注册中心，基于`eureka`，访问 [http://localhost:80/](http://localhost:80/) 即可看到服务注册中心的信息

### teclan-springcloud-server1

服务提供者，端口8081，提供 `/hello` 接口，

- URL: /hello

- 方法: GET

- 描述: 返回简单的问候信息

- 示例请求 

`
/hello?name=teclan
`

- 示例返回

`
 hello teclan
`

### teclan-springcloud-server2


服务提供者，端口8082，提供 `/bye` 接口，在此接口中，调用了 `server1`的`hello`接口

- URL: /bye

- 描述: 返回 `sever1` 的问候消息以及 `server2`自己的bye消息 

- 方法: GET

- 示例请求 

`
/bye?name=teclan
`

- 示例返回

```
hello laoshi

goodbyte laoshi
```
 
 

 
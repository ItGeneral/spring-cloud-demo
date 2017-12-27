# spring-cloud-demo  
## Spring cloud server
参照微博：http://blog.csdn.net/forezp/article/details/70148833

### 服务注册中心：eureka-server
1、eureka是一个服务注册和发现模块  
2、统一进行服务注册，所有的服务都注册在该服务上  
3、@EnableEurekaServer注解表明该服务是eureka服务，对外提供服务注册以及发现功能  
4、启动服务查看注册的服务有哪些`http://localhost:8761/`  

5、改造成高可用注册中心：新增application-peer1.properties，application-peer2.properties,注释application.properties  
6、启动java -jar eureka-server-0.0.1-SNAPSHOT.jar - -spring.profiles.active=peer1  
      java -jar eureka-server-0.0.1-SNAPSHOT.jar - -spring.profiles.active=peer2 
   在启动eureka-client  访问localhost:8761或localhost:8761

### 客户端：eureka-client
1、@EnableEurekaClient注解表明该服务是eureka服务，可以发现注册的服务

### 服务消费者(ribbon+restTemplate) service-ribbon (负载均衡服务)
1、服务与服务之间的通讯通过ribbon+restTemplate  
2、ribbon是一个负载均衡客户端，可以很好的控制http和tcp的一些行为  
3、在RestTemplate上面添加@LoadBalanced注解，使其具备负载均衡的功能  
4、向注册中心eureka-server调用eureka-client服务的接口：  
`restTemplate.getForObject("http://eureka-client/testHi?name=" + name, String.class)`  
5、`一个服务注册中心，eureka-server,端口为8761
 eureka-client工程跑了两个实例，端口分别为8762,8763，分别向服务注册中心注册
 sercvice-ribbon端口为8764,向服务注册中心注册
 当sercvice-ribbon通过restTemplate调用eureka-client的testHi接口时，
 因为用ribbon进行了负载均衡，会轮流的调用eureka-client：8762和8763 两个端口的testHi接口`  
6、@EnableDiscoveryClient注解发现服务
7、启动服务调用`http://localhost:8764/serviceRibbon?name=ribbon`刷新几次看端口变化
 
### 服务消费者(Feign) service-feign(负载均衡服务)
1、Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单， Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。  
2、Feign 采用的是基于接口的注解  
3、@EnableFeignClients注解开启Feign的功能  
4、在接口类上加注解@FeignClient(value = "eureka-client")：指定调用eureka-client服务  
5、在接口方法中加@RequestMapping(value = "/testHi", method = RequestMethod.GET)：表明调用eureka-client服务的/testHi接口  
6、启动服务调用`http://localhost:8765/feignHi?name=feign`刷新几次看端口变化  
7、feign是一个伪客户端，即它不做任何的请求处理。Feign通过处理注解生成request，从而实现简化HTTP API开发的目的，即开发人员可以使用注解的方式定制request api模板，在发送http request请求之前，feign通过处理注解的方式替换掉request模板中的参数，这种实现方式显得更为直接、可理解
8、feign实现原理：  
首先通过@EnableFeignCleints注解开启FeignCleint  
根据Feign的规则实现接口，并加@FeignCleint注解  
程序启动后，会进行包扫描，扫描所有的@FeignCleint的注解的类，并将这些信息注入到ioc容器中。  
当接口的方法被调用，通过jdk的代理，来生成具体的RequesTemplate  
RequesTemplate在生成Request  
Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是Okhttp  
最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡。  



### Zuul路由：service-zuul
1、配置以/api-a/ 开头的请求都转发给service-ribbon服务  
zuul.routes.api-a.path=/api-a/*  
zuul.routes.api-a.serviceId=service-ribbon  
2、配置以/api-b/开头的请求都转发给service-feign服务  
zuul.routes.api-b.path=/api-b/*  
zuul.routes.api-b.serviceId=service-feign  
3、启动服务访问  
http://localhost:8769/api-a/serviceRibbon?name=forezp  
http://localhost:8769/api-b/feignHi?name=forezp 
输出结果一样的  
4、添加过滤器MyFilter，访问http://localhost:8769/api-a/hi?name=forezp和 http://localhost:8769/api-a/hi?name=forezp&token=22查看过滤器是否生效  

### config-server 配置服务端
1、启动服务，调用http://localhost:8888/foo/dev  
返回结果：{"name":"foo","profiles":["dev"],"label":"master", "version":"792ffc77c03f4b138d28e89b576900ac5e01a44b","state":null,"propertySources":[]}  
证明配置服务中心可以从远程程序获取配置信息。  
2、配置远程仓库git地址、分支等信息  

### config-client 配置客户端
1、配置读取配置信息的服务网址、服务名称、注册中心等  
2、配置消息总线bus(需先安装rabbitmq),pom引入相应的jar包  
3、修改配置信息，post请求http://localhost:8881/bus/refresh，再访问http://localhost:8881/hi，发现修改的配置已生效  

### server-zipkin
1、主要作用使用ZipkinServer 的功能，收集调用数据，并展示  
2、启动服务，并访问http://localhost:9411/查看显示信息  
3、可以查看find a trace和dependencies  

### service-miya  
1、对外暴露miya接口 访问http://localhost:8989/miya

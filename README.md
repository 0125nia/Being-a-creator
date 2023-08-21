# BeCreators

#### 介绍

*爪哇部落第四次考核*

基于Reactor模型的自定义简易k-v数据库

#### 特点
- 采用多个设计模式优化程序
- 使用线程池,缓存池等优化性能
- 含有两种持久化方式(目前只能使用追加日志持久化)
- 服务器一个线程可以处理n个来自不同socket的请求

#### 程序入口

##### 服务端入口
com/nia/DataBase.java

##### 客户端入口
com/nia/ClientLauncher.java

运行`DataBase`开启服务端,等待客户端连接
运行`ClientLauncher`开启服务端
在">"后输入指令进行操作
服务端响应数据回写客户端

#### 指令列表
> 不同数据类型数据的key不可以相同
##### 字符串指令

```
//添加字符串
set [key] [value]
//获取字符串
get [key]
//删除字符串
del [key]
```

##### 集合指令

```
//往key中加入新的成员
sadd [key] [member1] [member2] ...
//查看集合中所有成员
smembers [key]
//判断某个元素是不是集合中的成员
sismember [key] [member]
//删除集合中的成员
srem [key] [member]
```

##### 链表指令

```
//从左侧添加元素到链表
lpush [key] [value]
//从右侧添加元素到链表
rpush [key] [value]
//删除左侧的元素
lpop [key]
//删除右侧的元素
rpop [key]
//遍历
range [key] [start] [end]
//链表长度
len [key]
//删除key对应的链表
ldel [key]
```

##### 映射指令

```
//添加映射元素
hset [key] [field] [value]
//获取映射元素
hget [key] [field]
//删除元素
hdel [key] [field]
hdel [key] 
```

##### 其他指令
```
//保存数据
save
//后台保存数据
bgsave
//清空缓存
flushdb
//设置key过期时间
expire [key] [delay]
//查看过期时间指令
ddl [key] 
```








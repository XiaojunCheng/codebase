# [SSDB网络协议](https://github.com/ideawu/ssdb-docs/blob/master/src/zh_cn/protocol.md)

## 报文

```
Packet := Block+ '\n'
Block  := Size '\n' Data '\n'
Size   := literal_integer
Data   := size_bytes_of_data
```

## 请求

```
Request := Cmd Blocks*
Cmd     := Block
```

请求命令包括: ```get, set, del, ...```

## 响应

```
Response := Status Block*
Status   := Block
```

响应状态码包括: ```ok, not_found, error, fail, client_error```

## 示例

用 telnet 或者 nc 命令连接到 SSDB 服务器, 然后输入下面的代码(用最后一行空行结束):

```
3
get
3
key

```

你将看到类似这样的响应:

```
2
ok
3
val

```

NOTE: `Data` 可以包含任意字符, 包括 `\r, \n, \0...`, 所以你__不要__认为 `Data` 里面不会出现这些字符.


# [Redis网络协议](http://redis.io/topics/protocol)

[Redis网络协议-中文](http://www.redis.cn/topics/protocol.html)
# Spring CAS Demo App

## 1. 配置hosts
```
127.0.0.1	cas.server.io
127.0.0.1	cas.client.io
```
## 2. 配置nginx.conf
```
upstream myapp1 {
	server 127.0.0.1:8081;
	server 127.0.0.1:8082;
}

server {
	listen       80;
	server_name  cas.server.io;

	location / {
		proxy_pass   http://127.0.0.1:8080;
	}
}

server {
    listen       80;
    server_name  cas.client.io;

    location / {
        proxy_pass http://myapp1;
    }
}
```

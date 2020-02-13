## 1.windows下配置nginx
### 1.下载nginx并解压
### 2.反向代理配置
修改nginx.conf文件
```
server {
    listen       80;        		#监听80端口
    server_name  manage.leyou.com; 	#监听的域名
    location / {            		#转发或处理
        proxy_pass http://localhost:9001/; 
    }
    error_page   500 502 503 504  /50x.html;#错误页
    location = /50x.html {
        root   /usr/share/nginx/html;
    }
}
```

### 3.cmd命令
启动： nginx.exe 或者 start nginx  
启动是否成功： tasklist /fi "imagename eq nginx.exe"  
80端口是否被占用： netstat -ano | findstr 0.0.0.0:80 或 netstat -ano | findstr "80"  
重启： nginx -s reload  
快速停止： nginx -s stop
平滑停止： nginx -s quit



## 
/opt/nginx/logs-error.log排查问题
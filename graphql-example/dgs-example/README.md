# GraphQL文件上传

### 单文件上传
使用curl发送请求：

```
curl -X POST http://localhost:8082/graphql --header "graphql-require-preflight:true" --form "operations={ \"query\": \"mutation ($file: Upload!) { fileUpload(file: $file) { id } }\" , \"variables\": {\"file\": null}}" --form "0=@E:/微信头像.jpg" --form "map={\"0\": [\"variables.file\"]}"
```

使用 Altair GraphQL 浏览器插件进行测试，访问地址（http://localhost:8082/altair/index.html）
需要添加请求头（graphql-require-preflight:true）查询代码如下：

```
mutation ($file: Upload!) {
  fileUpload(file: $file) {
    id
  }
}
```

### 多文件上传
使用curl发送请求：

```
curl -X POST http://localhost:8082/graphql --header "graphql-require-preflight:true" --form "operations={ \"query\": \"mutation ($files: [Upload!]!) { multiFileUpload(files: $files) { id } }\" , \"variables\": {\"files\": [null, null]}}" --form "0=@E:/微信头像.jpg" --form "1=@E:/微信头像2.jpg" --form "map={\"0\": [\"variables.files.0\"], \"1\": [\"variables.files.1\"]}"
```

使用 Altair GraphQL 浏览器插件进行测试，访问地址（http://localhost:8082/altair/index.html）
需要添加请求头（graphql-require-preflight:true）查询代码如下：

```
mutation ($files: [Upload!]!) {
  multiFileUpload(files: $files) {
    id
  }
}
```

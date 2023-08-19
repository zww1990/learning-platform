# GraphQL文件上传

### 单文件上传
使用curl发送请求：

```
curl -X POST http://localhost:8080/graphql --form "operations={ \"query\": \"mutation FileUpload($file: Upload!) {fileUpload(file: $file)}\" , \"variables\": {\"file\": null}}" --form "0=@E:/微信头像.jpg" --form "map={\"0\": [\"variables.file\"]}"
```

### 多文件上传
使用curl发送请求：

```
curl -X POST http://localhost:8080/graphql --form "operations={ \"query\": \"mutation FilesUploads($files: [Upload!]!) {multiFileUpload(files: $files)}\" , \"variables\": {\"files\": [null, null]}}" --form "0=@E:/微信头像.jpg" --form "1=@E:/微信头像2.jpg" --form "map={\"0\": [\"variables.files.0\"], \"1\": [\"variables.files.1\"]}"
```

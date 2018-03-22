# 我的标题


<a name="overview"></a>
## Overview
我的描述


### Version information
*Version* : 1.0.0


### Contact information
*Contact* : 我的名字  
*Contact Email* : 我的邮箱


### License information
*License* : 我的许可证  
*License URL* : 我的许可证网址  
*Terms of service* : 服务条款的网址


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* API示例 : Demo Controller




<a name="paths"></a>
## Paths

<a name="deleteusingdelete"></a>
### 删除用户信息
```
DELETE /demo/delete/{id}
```


#### Description
按id删除用户信息，使用路径传递id


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|用户id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `\*/*`


#### Tags

* API示例


<a name="getusingget"></a>
### 查询用户信息
```
GET /demo/get
```


#### Description
按id查询用户信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**id**  <br>*required*|用户id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[UserModel](#usermodel)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `\*/*`


#### Tags

* API示例


<a name="jsonusingpost"></a>
### 提交json格式数据
```
POST /demo/json
```


#### Description
提交json格式数据时，请求内容类型必须是application/json


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**user**  <br>*required*|用户模型|[UserModel](#usermodel)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[UserModel](#usermodel)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `\*/*`


#### Tags

* API示例


<a name="uploadusingpost"></a>
### 文件上传
```
POST /demo/upload
```


#### Description
文件上传的请求内容类型必须是multipart/form-data


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**FormData**|**file**  <br>*required*|文件|file|
|**FormData**|**userId**  <br>*required*|用户ID|integer (int32)|
|**FormData**|**userName**  <br>*required*|用户名|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< object > array|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `multipart/form-data`


#### Produces

* `\*/*`


#### Tags

* API示例


<a name="userusingput"></a>
### 请求头传参
```
PUT /demo/user
```


#### Description
设置请求头User-Info作为参数的名称，参数值是json字符串，需要经过base64转码，否则视为无效参数


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Header**|**User-Info**  <br>*required*|用户信息|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[UserModel](#usermodel)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `\*/*`


#### Tags

* API示例




<a name="definitions"></a>
## Definitions

<a name="inputstream"></a>
### InputStream
*Type* : object


<a name="usermodel"></a>
### UserModel
用户模型


|Name|Description|Schema|
|---|---|---|
|**userId**  <br>*required*|用户ID|integer (int32)|
|**userName**  <br>*required*|用户名|string|






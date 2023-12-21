import signrequest

if __name__ == '__main__':
    # 接口调用测试
    sign_request = signrequest.SignRequest()
    params = {'current': 1, 'pageSize': 500}
    # resp = sign_request.get("/open-api/organization/v2/companies", params)
    resp = sign_request.get("/open-api/supos/oodm/v2/template/system/mes/instances", {})

import signrequest

if __name__ == '__main__':
    # supOS地址
    supos_url = "http://192.168.60.21:8080"
    # 获取应用的ak
    app_ak = "aeb6e087881ca5639a919312bfba0d0c"
    # 获取应用的sk
    app_sk = "1e0d361aa22af5563b5be7ad7ce86507"

    # 接口调用测试
    sign_request = signrequest.SignRequest(supos_url, app_ak, app_sk)
    params = {'current': 1, 'pageSize': 500}
    # resp = sign_request.get("/open-api/organization/v2/companies", params)
    resp = sign_request.get("/open-api/supos/oodm/v2/template/system/mes/instances", {})

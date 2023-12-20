import signrequest

if __name__ == '__main__':
    sign_request = signrequest.SignRequest()
    params = {'current': 1, 'pageSize': 500}
    resp = sign_request.get("/open-api/organization/v2/companies", params)

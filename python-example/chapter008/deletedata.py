import requests
import json

if __name__ == '__main__':
    token = 'MWVDv_lckN49s24GEgkb61jgntxCZ6KoJduqc8-YFn5jEFBSMwBl9M6d_2wMk16fUSt06WMTUdJGuZNvCCVQxQ=='
    org = 'zww'
    bucket = 'test_1'
    url = f'http://localhost:8086/api/v2/delete?bucket={bucket}&org={org}'
    print('url:', url)

    headers = {
        'Content-Type': 'application/json',
        'Authorization': f'Token {token}'
    }
    print('headers:', headers)

    params = json.dumps({
        'start': '2024-01-01T00:00:00Z',
        'stop': '2025-01-01T00:00:00Z'
    })
    print('params:', params)

    response = requests.post(url=url, headers=headers, data=params)
    print('response:', response)

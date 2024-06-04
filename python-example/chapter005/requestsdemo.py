import requests
import json
import redis

if __name__ == '__main__':
    response = requests.get(url='http://222.74.84.178:40080/prod-api/admin-api/gn/supos/attribute/current/silo',
                            headers={
                                'Authorization': 'Bearer a7db76aabbe34324992a5ed429a53c5c'
                            })
    data = response.json()
    data = data.get('data')
    # print(json.dumps(data, indent=2))
    rdb = redis.Redis(host='172.16.200.181',
                      port=6379,
                      db=15,
                      decode_responses=True)
    # print(rdb.execute_command('keys *'))
    rdb.set('SUPOS_SILO_DATA', json.dumps(data))
    print(rdb.get('SUPOS_SILO_DATA'))

import redis
import json
from os.path import expanduser

if __name__ == '__main__':
    with open(expanduser('data.json')) as f:
        data = json.load(f)
        data = data.get('data', {})

    # print(data)

    rdb = redis.Redis(host='172.16.200.181', port=6379, db=15, decode_responses=True)
    # keys = rdb.execute_command('keys *')
    # for key in keys:
    #     print(key)
    redisKey = 'THREE_WAY_DATA'
    for key, value in data.items():
        # print(key, '=', value)
        rdb.hset(redisKey, key, json.dumps(value, ensure_ascii=False))

    print(redisKey, ':', rdb.hgetall(redisKey))

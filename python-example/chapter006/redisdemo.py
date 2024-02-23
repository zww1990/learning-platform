import redis

if __name__ == '__main__':
    rdb = redis.Redis(host='localhost',
                      port=6379,
                      password='admin@2024',
                      decode_responses=True)

    res = rdb.execute_command('info')

    print('redis_version:', res['redis_version'])

    rdb.set('applicationcontext', 'hello 王五')

    print('applicationcontext:', rdb.get('applicationcontext'))

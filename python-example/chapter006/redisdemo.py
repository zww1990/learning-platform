import redis

if __name__ == '__main__':
    rdb = redis.Redis(host='localhost',
                      port=6379,
                      password='admin@2024',
                      decode_responses=True)

    # 执行命令
    res = rdb.execute_command('info')
    print('redis_version:', res['redis_version'])

    # 读写字符串
    # rdb.set('applicationcontext', 'hello 王五')
    print('applicationcontext:', rdb.get('applicationcontext'))

    # 读写哈希表
    # rdb.hset('users', '1001', '李四')
    print('users:', rdb.hgetall('users'))

    # 读写列表
    # rdb.lpush('computer', 'alienware', 'msi', 'rog')
    print('computer:', rdb.lrange('computer', 0, -1))

    # 读写集合
    # rdb.sadd('onepiece', '路飞', '娜美', '索隆')
    print('onepiece:', rdb.smembers('onepiece'))

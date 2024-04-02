# 这是一个示例 Python 脚本。

import sys
from datetime import datetime
import pytz


def print_hi(name):
    print(f'Hi, {name}')


if __name__ == '__main__':
    print(sys.version)
    print_hi('PyCharm')
    print(datetime.now(pytz.timezone('Asia/Shanghai')))
    print(datetime.now(pytz.UTC))
    # [print(item) for item in pytz.all_timezones]
    # print(pytz.timezone('Asia/Shanghai'))

# 这是一个示例 Python 脚本。

import sys
import datetime


def print_hi(name):
    print(f'Hi, {name}')


if __name__ == '__main__':
    print(sys.version)
    print_hi('PyCharm')
    print(datetime.datetime.now())
    print(datetime.datetime.now(datetime.UTC))

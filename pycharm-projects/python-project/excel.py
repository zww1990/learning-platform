import pandas as pd
import os
import numpy as np
from _datetime import datetime


def is_excel(file_name: str):
    tmp = file_name.lower()
    return tmp.endswith('.xls') or tmp.endswith('.xlsx')


def make_dirs(dir_name: str):
    if not os.path.exists(dir_name):
        print(f'正在创建文件夹[ {dir_name} ]')
        os.makedirs(dir_name)
        print(f'已创建文件夹[ {dir_name} ]')


def merge_excel():
    src_dir = r"D:\合并\待合并的工作簿"
    tar_dir = r"D:\合并\已合并的工作簿"
    make_dirs(src_dir)
    make_dirs(tar_dir)
    files = list(filter(is_excel, os.listdir(src_dir)))
    if len(files) == 0:
        print(f'文件夹[ {src_dir} ]没有待合并的工作簿')
    else:
        data = {}
        for src_name in files:
            full_name = os.path.join(src_dir, src_name)
            print(f'正在读取工作簿[ {full_name} ]')
            df = pd.read_excel(full_name, header=None, sheet_name=None)
            for key, value in df.items():
                if key in data:
                    data[key] = np.concatenate((data[key], value.values))
                else:
                    data[key] = value.values
        now = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
        tar_name = os.path.join(tar_dir, f'{now}.xlsx')
        print(f'正在合并工作簿到[ {tar_name} ]')
        with pd.ExcelWriter(tar_name) as writer:
            for key, value in data.items():
                pd.DataFrame(value).to_excel(writer, sheet_name=key, index=False, header=False)
        print('工作簿合并完成')


if __name__ == '__main__':
    merge_excel()

import pandas as pd
import os
import math
from _datetime import datetime


def is_excel(file_name: str):
    tmp = file_name.lower()
    return tmp.endswith('.xls') or tmp.endswith('.xlsx')


def make_dirs(dir_name: str):
    if not os.path.exists(dir_name):
        print(f'正在创建文件夹[ {dir_name} ]')
        os.makedirs(dir_name)
        print(f'已创建文件夹[ {dir_name} ]')


def not_all_nan(items: list):
    ret = all(isinstance(i, float) and math.isnan(i) for i in items)
    if ret:
        print('正在删除空白行')
    return not ret


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
                    data[key].extend(list(filter(not_all_nan, value.values.tolist())))
                else:
                    data[key] = list(filter(not_all_nan, value.values.tolist()))
        now = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
        tar_name = os.path.join(tar_dir, f'{now}.xlsx')
        print(f'正在合并工作簿到[ {tar_name} ]')
        with pd.ExcelWriter(tar_name) as writer:
            for key, value in data.items():
                pd.DataFrame(value).to_excel(writer, sheet_name=key, index=False, header=False)
        print('工作簿合并完成')
    answer = input('是否继续合并?(Y/N): ')
    if answer.upper() == 'Y':
        merge_excel()
    else:
        print('程序运行结束。')


if __name__ == '__main__':
    merge_excel()

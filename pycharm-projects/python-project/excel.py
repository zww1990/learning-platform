import pandas as pd
import os
from _datetime import datetime


def is_excel(file_name: str):
    tmp = file_name.lower()
    return tmp.endswith('.xls') or tmp.endswith('.xlsx')


def merge_excel():
    src_dir = "D:\\合并\\待合并的工作簿"
    tar_dir = "D:\\合并\\已合并的工作簿"
    if not os.path.exists(src_dir):
        print(f'正在创建文件夹[ {src_dir} ]')
        os.makedirs(src_dir)
        print(f'已创建文件夹[ {src_dir} ]')
    files = list(filter(is_excel, os.listdir(src_dir)))
    if len(files) == 0:
        print(f'文件夹[ {src_dir} ]没有待合并的工作簿')
    else:
        dfs = []
        for src_name in files:
            full_name = os.path.join(src_dir, src_name)
            print(full_name)
            df = pd.read_excel(full_name)
            # print(df.values)
            dfs.extend(df.values())
        now = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
        tar_name = os.path.join(tar_dir, f'{now}.xlsx')
        print(tar_name)
        result = pd.concat(dfs)
        result.to_excel(tar_name, index=False)


if __name__ == '__main__':
    merge_excel()

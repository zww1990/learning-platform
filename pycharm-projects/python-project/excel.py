import pandas as pd
import os
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
        dfs = []
        for src_name in files:
            full_name = os.path.join(src_dir, src_name)
            print(f'正在读取工作簿[ {full_name} ]')
            df = pd.read_excel(full_name, header=None, sheet_name=None)
            dfs.extend(df.values())
        now = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
        tar_name = os.path.join(tar_dir, f'{now}.xlsx')
        print(f'正在合并工作簿到[ {tar_name} ]')
        result = pd.concat(dfs)
        result.to_excel(tar_name, index=False, header=False)
        print('工作簿合并完成')


def writer_excel():
    file_path = r'D:\合并\demo.xlsx'
    data_1 = [
        {'姓名': '张三', '性别': '男'},
        {'姓名': '李四', '性别': '女'}
    ]
    data_2 = [
        {'姓名': '张三风', '性别': '男'},
        {'姓名': '李四民', '性别': '女'}
    ]
    with pd.ExcelWriter(file_path) as writer:
        pd.DataFrame(data_1).to_excel(writer, sheet_name='table-1', index=False)
        pd.DataFrame(data_2).to_excel(writer, sheet_name='table-2', index=False)
    print(f'写入[ {file_path} ]完毕')


if __name__ == '__main__':
    writer_excel()

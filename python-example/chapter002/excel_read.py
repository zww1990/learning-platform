import pandas as pd
import json

if __name__ == '__main__':
    full_name = "D:\\工作文档\\水一方筒仓地址.xlsx"
    df = pd.read_excel(full_name, header=None, sheet_name=None)
    # 累计流量 # 瞬时流量 # 计划流量 # 断料报警 # 皮带跑偏 # 皮带秤故障
    values = df.get('皮带秤故障').values
    newList = [dict(zip(['dataAddress', 'dataType', 'name'], [item[1], item[3], item[2]])) for item in values[1:]]
    print(len(newList))
    print(json.dumps(newList, indent=2, ensure_ascii=False))

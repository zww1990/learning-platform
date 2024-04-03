import pandas as pd
import json

if __name__ == '__main__':
    full_name = "D:\\工作文档\\siloposition.xlsx"
    df = pd.read_excel(full_name, header=None, sheet_name=None)
    values = df.get('Sheet2').values
    newList = [dict(zip(values[0], item)) for item in values[1:]]
    print(len(newList))
    print(json.dumps(newList, indent=2))

import requests

if __name__ == '__main__':
    products = {
        'IIU': 'IntelliJ IDEA Ultimate',
        # 'IIC': 'IntelliJ IDEA Community',
        'PCP': 'PyCharm Professional',
        # 'PCC': 'PyCharm Community Edition',
        'WS': 'WebStorm',
        'DG': 'DataGrip'
    }
    join = ','.join(products.keys())
    # print(join)
    url = f'https://data.services.jetbrains.com/products/releases?code={join}&latest=true&type=release'
    # print(url)
    ides = []
    try:
        response = requests.get(url)
        # print(response.status_code)
        if response.status_code == 200:
            data = response.json()
            for key, value in products.items():
                items = data.get(key, [{}])
                item = items[0]
                info = {
                    'name': value,
                    'date': item.get('date'),
                    'version': item.get('version'),
                    'majorVersion': item.get('majorVersion'),
                    'build': item.get('build'),
                }
                ides.append(info)
                # print(key, '=', info)
        else:
            raise Exception(f'请求{url}接口错误')
    except Exception as err:
        print(err)
    else:
        print('name\t\t\tdate\t\tversion\t\tmajorVersion\tbuild')
        for ide in ides:
            name = ide.get('name')
            date = ide.get('date')
            version = ide.get('version')
            majorVersion = ide.get('majorVersion')
            build = ide.get('build')
            nd = '\t'
            if len(name) <= 10:
                nd = '\t' * 2
            mb = '\t' * 2
            print(f'{name}{nd}{date}\t{version}\t{majorVersion}{mb}{build}')

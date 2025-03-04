import requests
import json

def domain():
    products = {
        'QA': 'Aqua',
        'CL': 'CLion',
        'DG': 'DataGrip',
        'DS': 'DataSpell',
        'DC': 'dotCover',
        'DM': 'dotMemory',
        'DP': 'dotTrace',
        'GO': 'GoLand',
        'IIU': 'IntelliJ IDEA Ultimate',
        'IIC': 'IntelliJ IDEA Community',
        'PS': 'PhpStorm',
        'PCP': 'PyCharm Professional',
        'PCC': 'PyCharm Community',
        'RS': 'ReSharper',
        'RC': 'ReSharper C++',
        'RSU': 'ReSharper Ultimate',
        'RD': 'Rider',
        'RM': 'RubyMine',
        'RR': 'RustRover',
        'WS': 'WebStorm',
        'FL': 'Fleet',
    }
    join = ','.join(products.keys())
    # print(join)
    url = f'https://data.services.jetbrains.com/products/releases?code={join}&latest=true&type=release,preview'
    # print(url)
    ides = []
    print('正在检查JetBrains开发者工具版本:')
    try:
        response = requests.get(url)
        # print(response.status_code)
        if response.status_code == 200:
            data = response.json()
            for key, value in products.items():
                items = data.get(key, [{}])
                item = items[0]
                item.pop('patches')
                item.pop('uninstallFeedbackLinks')
                item.pop('whatsnew')
                item.pop('notesLink')
                item.pop('printableReleaseType')
                item.pop('licenseRequired')
                item['name'] = value
                info = {
                    'name': value,
                    'date': item.get('date'),
                    'version': item.get('version'),
                    'majorVersion': item.get('majorVersion'),
                    'build': item.get('build'),
                    'type': item.get('type'),
                }
                ides.append(info)
                # print(key, '=', info)
            with open('data.json', 'w') as file:
                file.write(json.dumps(data, indent=2, ensure_ascii=False))
        else:
            raise Exception(f'请求{url}接口错误')
    except Exception as err:
        print(err)
    else:
        print('Name\t\t\tDate\t\tVersion\t\tMajorVersion\tBuild\t\tType')
        for ide in ides:
            name = ide.get('name')
            # print(len(name))
            date = ide.get('date')
            version = ide.get('version')
            major_version = ide.get('majorVersion')
            build = ide.get('build')
            _type = ide.get('type')
            nd = '\t'
            if len(name) <= 6:
                nd = '\t' * 3
            elif len(name) <= 15:
                nd = '\t' * 2
            mb = '\t' * 2
            vm = '\t'
            if len(version) <= 7:
                vm = '\t' * 2
            bt = '\t'
            if len(build) <= 7:
                bt = '\t' * 2
            print(f'{name}{nd}{date}\t{version}{vm}{major_version}{mb}{build}{bt}{_type}')
    answer = input('是否重新运行?(Y/N): ')
    if answer.upper() == 'Y':
        domain()
    else:
        print('程序运行结束。')

if __name__ == '__main__':
    domain()

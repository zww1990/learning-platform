def say_hello(text):
    print(f'你输入的内容是: {text}')
    answer = input('是否继续输入?(Y/N): ')
    if answer == 'Y':
        say_hello(input('请输入你的内容: '))


def ifelse(var1, var2):
    if var1 is None:
        print(f'var1 is {var1} !!!')
    elif var2 is None:
        print(f'var2 is {var2} !!!')
    else:
        print(f'var1 = {var1}, var2 = {var2}')


if __name__ == '__main__':
    # say_hello(input('请输入你的内容: '))
    ifelse(None, 2)
    print('--------------------------')
    ifelse(1, None)
    print('--------------------------')
    ifelse(None, None)
    print('--------------------------')
    ifelse(1, 2)

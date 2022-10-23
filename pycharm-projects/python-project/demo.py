def say_hello(text):
    print(f'你输入的内容是: {text}')
    answer = input('是否继续输入?(Y/N): ')
    if answer == 'Y':
        say_hello(input('请输入你的内容: '))


if __name__ == '__main__':
    say_hello(input('请输入你的内容: '))

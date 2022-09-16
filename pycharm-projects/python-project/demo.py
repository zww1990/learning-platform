def say_hello(text):
    print(f'你输入的内容是: {text}')


if __name__ == '__main__':
    print("请输入你的内容: ")
    say_hello(input())

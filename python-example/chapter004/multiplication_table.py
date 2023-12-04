def use_while():
    i = 1
    while i <= 9:
        j = 1
        while j <= i:
            print(f'{j} * {i} = {j * i}', end='\t')
            j += 1
        print()
        i += 1


def use_for():
    for i in range(1, 10):
        for j in range(1, i + 1):
            print(f'{j} * {i} = {j * i}', end='\t')
        print()


if __name__ == '__main__':
    use_for()

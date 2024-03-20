import json


def array_extend_demo():
    arr1 = []
    arr2 = [1, 2, 3]
    arr3 = [4, 5, 6]

    # arr1 = arr1 + arr2 + arr3
    arr1.extend(arr2)
    arr1.extend(arr3)
    print(arr1)


def dict_zip_for_in_enumerate_demo():
    data = [11, 22, 33, 44, 55, 66, 77, 88, 99, 1010]
    newlist = [dict(zip(['read_add', 'read_value'], [index + 30001, item])) for index, item in enumerate(data)]
    print(json.dumps(newlist, indent=2))


if __name__ == '__main__':
    # array_extend_demo()
    dict_zip_for_in_enumerate_demo()

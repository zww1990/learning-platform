if __name__ == '__main__':
    arr1 = []
    arr2 = [1, 2, 3]
    arr3 = [4, 5, 6]

    # arr1 = arr1 + arr2 + arr3
    arr1.extend(arr2)
    arr1.extend(arr3)
    print(arr1)

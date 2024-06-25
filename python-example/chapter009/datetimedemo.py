from datetime import date, timedelta

if __name__ == '__main__':
    today = date.today()
    print('今天是', today)

    first_day_of_this_month = date(today.year, today.month, 1)
    print('本月第一天是', first_day_of_this_month)

    year = today.year
    month = today.month
    if month == 1:
        year -= 1
        month = 12
    else:
        month -= 1
    first_day_of_last_month = date(year, month, 1)
    print('上月第一天是', first_day_of_last_month)

    last_day_of_last_month = first_day_of_this_month - timedelta(days=1)
    print('上月最后一天是', last_day_of_last_month)

    print(date(2024, 6, 1) == date(2024, 6, 1))
    print(date(2024, 6, 1) == date(2024, 5, 1))
    print(date(2024, 6, 1) == today)
    print(date(2024, 6, 1) == first_day_of_this_month)
    print(date(2024, 6, 1) == first_day_of_last_month)
    print(date(2024, 6, 1) == last_day_of_last_month)

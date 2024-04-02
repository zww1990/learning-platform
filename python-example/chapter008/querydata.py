import influxdb_client

if __name__ == '__main__':
    token = 'MWVDv_lckN49s24GEgkb61jgntxCZ6KoJduqc8-YFn5jEFBSMwBl9M6d_2wMk16fUSt06WMTUdJGuZNvCCVQxQ=='
    org = 'zww'
    url = 'http://localhost:8086/'
    bucket = 'test_1'

    client = influxdb_client.InfluxDBClient(url=url, token=token, org=org)
    query_api = client.query_api()

    query = f"""
                from(bucket: "{bucket}")
                |> range(start: -12h)
                |> filter(fn: (r) => r._measurement == "measurement1")
            """
    tables = query_api.query(query, org=org)

    for table in tables:
        for record in table.records:
            print(record)

from influxdb_client import Point, InfluxDBClient
from influxdb_client.client.write_api import SYNCHRONOUS

if __name__ == '__main__':
    token = 'MWVDv_lckN49s24GEgkb61jgntxCZ6KoJduqc8-YFn5jEFBSMwBl9M6d_2wMk16fUSt06WMTUdJGuZNvCCVQxQ=='
    org = 'zww'
    url = 'http://localhost:8086/'
    bucket = 'test_1'

    write_client = InfluxDBClient(url=url, token=token, org=org)
    write_api = write_client.write_api(write_options=SYNCHRONOUS)

    for value in range(5):
        point = (
            Point.measurement('measurement1')
            .tag('tagname1', 'tagvalue1')
            .tag('tagname2', 'tagvalue2')
            .field('field1', value)
        )
        print('point:', point)
        write_api.write(bucket=bucket, record=point)

    write_client.close()

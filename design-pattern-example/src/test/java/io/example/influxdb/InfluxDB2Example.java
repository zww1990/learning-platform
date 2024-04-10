package io.example.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

public class InfluxDB2Example {
    public static void main(String[] args) {
        String token = "RWEoTu9ags801Pr6Sf5abtUST0Dv19Tp1hQE1Rh-e9QC0KTGErTq9utMnBucreOEgPibYbX2zqXGcvey21t3hA==";
        String bucket = "test_1";
        String org = "wfc";
        String url = "http://192.168.60.50:8086";

        try (InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray())) {
//            write(client, bucket, org);
            query(client, org);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write(InfluxDBClient client, String bucket, String org) {
        Point point = Point
                .measurement("mem")
                .addTag("host", "host1")
                .addField("used_percent", 23.43234543)
                .time(Instant.now(), WritePrecision.NS);
        System.out.println("point = " + point.toLineProtocol());

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writePoint(bucket, org, point);
    }

    public static void query(InfluxDBClient client, String org) {
        String query = "from(bucket: \"test_1\") |> range(start: -1h)";
        List<FluxTable> tables = client.getQueryApi().query(query, org);
        System.out.println("tables.size() = " + tables.size());

        for (FluxTable table : tables) {
            System.out.println("table.getRecords().size() = " + table.getRecords().size());
            for (FluxRecord record : table.getRecords()) {
                System.out.println(record);
            }
        }
    }
}

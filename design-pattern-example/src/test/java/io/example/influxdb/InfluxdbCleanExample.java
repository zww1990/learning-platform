package io.example.influxdb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InfluxdbCleanExample {
    public static void main(String[] args) throws Exception {
        String token = "RWEoTu9ags801Pr6Sf5abtUST0Dv19Tp1hQE1Rh-e9QC0KTGErTq9utMnBucreOEgPibYbX2zqXGcvey21t3hA==";
        String bucket = "test_1";
        String org = "wfc";
        String url = "http://192.168.60.50:8086";

        String fullUrl = String.format("%s/api/v2/delete?bucket=%s&org=%s", url, bucket, org);
        System.out.println("fullUrl = " + fullUrl);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                            "start": "2024-01-01T00:00:00Z",
                            "stop": "2025-01-01T00:00:00Z"
                        }
                        """))
                .uri(URI.create(fullUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.err.println(response);
    }
}

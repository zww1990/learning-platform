WITH RECURSIVE transfer (start_station, stop_station, stops, path) AS (
    SELECT station_name, next_station, 1 as stops, ARRAY[station_name::text, next_station::text] as path
    FROM beijing_subway
    WHERE station_name = '阜成门'
    UNION ALL
    SELECT t.start_station, s.next_station, (t.stops + 1) as stops, (t.path || ARRAY[s.next_station::text]) as path
    FROM transfer as t
             JOIN beijing_subway as s
                  ON t.stop_station = s.station_name AND NOT s.next_station = ANY(t.path)
)
SELECT * FROM transfer
WHERE stop_station = '丰台科技园';

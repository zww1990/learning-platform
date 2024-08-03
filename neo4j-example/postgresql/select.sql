WITH RECURSIVE paths (start_station, stop_station, stops, path) AS (
    SELECT station_name, next_station, 1, ARRAY[station_name::text, next_station::text]
    FROM beijing_subway WHERE station_name = '阜成门'
    UNION ALL
    SELECT p.start_station, e.next_station, p.stops + 1, p.path || ARRAY[e.next_station::text]
    FROM paths p
             JOIN beijing_subway e
                  ON p.stop_station = e.station_name AND NOT e.next_station = ANY(p.path)
)
SELECT * FROM paths WHERE stop_station = '丰台科技园' limit 10;

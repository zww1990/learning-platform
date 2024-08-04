with recursive transfer(start_station, stop_station, stops, paths) as (
    select station_name, next_station, 1 as stops, json_array(station_name, next_station) as paths
    from beijing_subway
    where station_name = '阜成门'
    union all
    select t.start_station, s.next_station, (t.stops + 1) as stops, json_array_append(t.paths, '$', s.next_station) as paths
    from transfer as t
             join beijing_subway as s
                  on t.stop_station = s.station_name and json_contains(t.paths, json_quote(s.next_station)) = 0
)
select * from transfer
where stop_station = '丰台科技园';

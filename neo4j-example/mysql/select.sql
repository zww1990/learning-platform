with recursive transfer(start_station, stop_station, stops, paths) as (
    select station_name, next_station, 1 as stops, cast(concat(station_name, '->', next_station) as char(1000)) as paths
    from beijing_subway
    where station_name = '阜成门'
    union all
    select t.start_station, s.next_station, (t.stops + 1) as stops, concat(t.paths, '->', s.next_station) as paths
    from transfer as t
             join beijing_subway as s
                  on t.stop_station = s.station_name and instr(t.paths, s.next_station) = 0
)
select * from transfer
where stop_station = '北京西站';

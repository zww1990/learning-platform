导入本地csv文件
LOAD CSV WITH HEADERS FROM 'file:///station.csv' AS row
MERGE (:Station {id: toInteger(row.id), name: row.name});

load csv with headers from 'file:///line1.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:一号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line2.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:二号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line4.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:四号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line5.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:五号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line6.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:六号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line7.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:七号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line8.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:八号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line9.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:九号线 {length: toInteger(row.length), line: row.line}] -> (to);

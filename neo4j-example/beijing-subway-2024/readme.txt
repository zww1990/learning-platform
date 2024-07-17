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

load csv with headers from 'file:///line10.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line11.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十一号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line13.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十三号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line14.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十四号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line15.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十五号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line16.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十六号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line17.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十七号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///line19.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:十九号线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineDXJC.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:大兴机场线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineXJ.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:西郊线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineS1.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:S1线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineYF.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:燕房线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineDX.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:大兴线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineCP.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:昌平线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineFS.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:房山线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineYZ.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:亦庄线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineBT.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:八通线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineSDJC.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:首都机场线 {length: toInteger(row.length), line: row.line}] -> (to);

load csv with headers from 'file:///lineYZT1.csv' as row
match (from: Station {name: row.startname}), (to: Station {name: row.endname})
merge (from) - [:亦庄T1线 {length: toInteger(row.length), line: row.line}] -> (to);

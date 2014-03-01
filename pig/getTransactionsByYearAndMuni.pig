/* Groups all recorder transaction by propertyId */

REGISTER avro-1.7.6.jar;
REGISTER avro-mapred-1.7.6.jar;

records = LOAD '../data/avro/BAY_AREA/recorder/*.avro' USING AvroStorage();

record_map = FOREACH records GENERATE MM_MUNI_NAME, SUBSTRING((chararray)SR_DATE_TRANSFER, 0, 4) as year;
B = GROUP record_map BY (year, MM_MUNI_NAME);
C = FOREACH B GENERATE group, COUNT(record_map);

STORE C INTO 'outputs/muni_year_aggr_transactions' USING PigStorage(',');

SET pig.pretty.print.schema true;
SET mapred.output.compress true
SET mapred.output.compression.codec org.apache.hadoop.io.compress.SnappyCodec
SET avro.output.codec snappy

REGISTER avro-1.7.6.jar;
REGISTER avro-mapred-1.7.6.jar;

recorder = LOAD 'outputs/grouped/grouped.avro' USING AvroStorage ();
assessor = LOAD '../data/avro/BAY_AREA/assessor/0.avro' USING AvroStorage();

combined = JOIN assessor BY SA_PROPERTY_ID LEFT OUTER, recorder by group;

STORE combined INTO 'outputs/joined' using AvroStorage('joined', '-doublecolons true');

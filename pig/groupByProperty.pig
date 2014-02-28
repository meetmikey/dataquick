SET mapred.output.compress true
SET mapred.output.compression.codec org.apache.hadoop.io.compress.SnappyCodec
SET avro.output.codec snappy

REGISTER avro-1.7.6.jar;
REGISTER json_simple-1.1.jar
REGISTER avro-mapred-1.7.6.jar;

records = LOAD '../data/avro/BAY_AREA/recorder/*' USING AvroStorage();

groups = GROUP records BY SR_PROPERTY_ID;

STORE groups into 'grouped' using AvroStorage('recorder_grouped', '-schemafile file:///Users/sagarmac/Code/real_estate/residential/dataquick/data/avroSchema/avro_recorder_grouped.avsc');
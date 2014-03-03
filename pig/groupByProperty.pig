/* Groups all recorder transaction by propertyId */
SET mapred.output.compress true
SET mapred.output.compression.codec org.apache.hadoop.io.compress.SnappyCodec
SET avro.output.codec $avroCompressionCodec

REGISTER avro-1.7.6.jar;
REGISTER avro-mapred-1.7.6.jar;

records = LOAD '../data/avro/BAY_AREA/recorder/*' USING AvroStorage();

groups = GROUP records BY SR_PROPERTY_ID;

STORE groups into 'outputs/grouped' using AvroStorage('recorder_grouped', '-schemafile $schemaFile');
Scripts to create a mongo database or avro file from dataquick fixed width files

*INITIATE VIRTUAL ENV*
+ virtualenv ENV
+ source /ENV/bin/activate

*TO INSTALL DEPENDENCIES*
+ pip install -r requirements.txt

*SCHEMAS*
+ /data/fixedWidth directory contains relevant schemas needed for processing dataquick files
+ /data/avroSchema directory contains relevant schemas needed for outputing avro files
+ Schemas are generated programatically from csv of layout files. To generate a schema see createFixedWidthSchema.py or createAvroSchema.py

*TO GENERATE SINGLE MONGO COLLECTION*
+ python processMongo.py <fixed width schema file path> <dataquick file path> <collection name>

*TO GENERATE AVRO FILE*
+ python processAvro.py <fixed width schema file path> <data file> <avro schema file> <avro output file>'
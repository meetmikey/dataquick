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
+ python processMongo.py &lt;fixed width schema file path&gt; &lt;dataquick file path&gt; &lt;collection name&gt;

*TO GENERATE AVRO FILE*
+ python processAvro.py &lt;fixed width schema file path&gt; &lt;data file&gt; &lt;avro schema file&gt; &lt;avro output file&gt;
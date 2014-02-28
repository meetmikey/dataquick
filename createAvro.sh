#!/bin/bash

echo 'starting jobs 1-4'
python processAvro.py data/fixedWidth/fw_assessor.json data/raw/BAY_AREA/assessor/0.txt data/avroSchema/avro_assessor.avsc data/avro/BAY_AREA/assessor/0.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/0.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/0.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/1.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/1.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/2.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/2.avro

echo 'starting jobs 3-6'
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/3.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/3.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/4.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/4.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/5.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/5.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/6.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/6.avro

echo 'starting jobs 7-9'
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/7.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/7.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/8.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/8.avro &
python processAvro.py data/fixedWidth/fw_recorder.json data/raw/BAY_AREA/recorder/9.txt data/avroSchema/avro_recorder.avsc data/avro/BAY_AREA/recorder/9.avro

wait
echo all jobs are done!

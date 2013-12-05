this repo is to create a mongo database from dataquick fixed file data

usage:
python create_schema.py <schema file> <data file>
python create_schema.py <schema file> <output file> <mongo collection name>

sample to process the dataquick fixedwith file using the schema
python process.py data/assessor.json data/SIDEKICK_ASSR_TEST.txt

sample to create a schema file from csv:
python create_schema.py ~/Dropbox/dataquick\ sample/ASSESSOR/Sidekick\ Labs\ Assessor\ Layout\ With\ Geocodes.csv ~/Dropbox/dataquick\ sample/ASSESSOR/assessor.json assessor

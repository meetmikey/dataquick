import avro
from avro import schema, datafile, io
import json
import sys

args = sys.argv

fileName = args[1]

recReader = io.DatumReader()

# Create a 'data file' (avro file) reader
dfReader = datafile.DataFileReader(open(fileName), recReader)

count = 0
# Read all records stored inside

for record in dfReader:
  count+=1
  
  if count %10000 == 0:
    print count

print count

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

print dfReader

for record in dfReader:
  if len(record['records']) >= 3:
    final = []
    for d in record['records']:
      print 'a new record'
      c  = {}
      for k,v in d.iteritems():
        if v != None:
          c[k] = v
      final.append(c)

    print json.dumps(final)
    #print json.dumps(record)
    break

print count

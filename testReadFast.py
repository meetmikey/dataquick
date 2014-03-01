import fastavro as avro
import sys

args = sys.argv

fileName = args[1]

with open(fileName, 'rb') as fo:
  reader = avro.reader(fo)
  schema = reader.schema
  count = 0
  
  for record in reader:
    print record
    break

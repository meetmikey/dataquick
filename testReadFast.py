import fastavro as avro
import sys

args = sys.argv

fileName = args[1]

with open(fileName, 'rb') as fo:
  reader = avro.reader(fo)
  schema = reader.schema
  count = 0
  
  for record in reader:
    count+=1
    #process_record(record)
    if count % 10000 == 0:
      print count

print count

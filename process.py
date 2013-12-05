"""
  Takes in json schema and writes content of fixed width dataquick text file into a mongo collection
"""

from pymongo import MongoClient
from pprint import pprint
import json
import sys

#connect to db
client = MongoClient('localhost', 27017)

db = client.dataquick

args = sys.argv

if len(args) < 3:
  print 'usage: python process.py <schema file> <data file>'
  sys.exit()

schemaFile = open(args[1] , 'r')
dataFile = open(args[2], 'r')

schema = schemaFile.read()

schemaJson = json.loads(schema)
mongoCollection = db[schemaJson['name']]
pprint(schemaJson)
batch = []
batchIndex = 0
totalIndex = 0

while True:
  line = dataFile.readline()
  if line == '':
    break
  lineIndex = 0
  record = {}

  for field in schemaJson['fields']:
    width = field['width']
    value = line[lineIndex:lineIndex+width].strip()

    if field['type'] != 'string':
      if value != '':
        if field['type'] == 'integer':
          try:
            value = int(value)
          except:
            value = float(value)
        elif field['type'] == 'float':
          value = float(value)

    record[field['name']] = value
    lineIndex+=width

  batch.append(record)
  batchIndex += 1

  if batchIndex == 1000:
    totalIndex += batchIndex
    print 'batch complete', totalIndex
    mongoCollection.insert(batch, w=0)
    batch = []
    batchIndex = 0

#do the last insert
if len(batch):
  mongoCollection.insert(batch, w=0)
'''
  Takes in dataquick layout csv file and generates .json file

  SAMPLE SCHEMA:

  {
    "namespace": "com.realkick",
    "type": "record",
    "name": "Assessment",
    "fields": [
       {"name": "name", "type": "string"},
       {"name": "favorite_number",  "type": ["int", "null"]},
       {"name": "favorite_color", "type": ["string", "null"]}
    ]
  }
'''

import sys
import json
from pprint import pprint

args = sys.argv

if len(args) < 3:
  print 'usage: python create_schema.py <schema file> <output file> <mongo collection name>'
  sys.exit()

csvFile = open(args[1] , 'r')
outFile = open(args[2], 'w')
collectionName = args[3]

schema = {
  "namespace": "com.realkick",
  "type": "record",
  "name": collectionName,
  "fields": [
  ]
}

lineIndex = 0

while True:
  line = csvFile.readline()
  if line == '':
    break

  lineIndex += 1

  #skip the first two lines
  if lineIndex > 2:
    split = line.split(',')

    name = split[1]
    emptyValues = split[7]

    if name not in ['FILLER', 'CR-LF', 'Total']:
      field = {
        'name' : name.strip()
      }

      fieldTypeFromCSV = split[3].strip()
      fieldTypeForAvro = 'int'

      if fieldTypeFromCSV == 'numeric':
        fieldTypeForAvro = 'float'
      elif fieldTypeFromCSV =='varchar':
        fieldTypeForAvro = 'string'

      if emptyValues == 'Yes':
        field['type'] = ["null"]
        field['type'].append(fieldTypeForAvro)
      else:
        field['type'] = fieldTypeForAvro

      schema['fields'].append(field)

schemaJSON = json.dumps(schema)

outFile.write(schemaJSON)
outFile.close()

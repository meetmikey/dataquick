'''
  Takes in dataquick layout csv file and generates .json file

  SAMPLE SCHEMA:

  {
    "name" : "avm",
    "fields" : [
              { "name" : "SA_PROPERTY_ID", "width" : 12, "type" : "number" },
              { "name" : "SA_SCM_ID", "width" : 12, "type" : "number" },
              { "name" : "MM_STATE_CODE", "width" : 2, "type" : "string" },
              { "name" : "MM_MUNI_NAME", "width" : 24, "type" : "string" },
              { "name" : "MM_FIPS_STATE_CODE", "width" : 5, "type" : "number" },
              { "name" : "MM_FIPS_MUNI_CODE", "width" : 7, "type" : "number" },
              { "name" : "MM_FIPS_COUNTY_NAME", "width" : 35, "type" : "string" },
              { "name" : "AVM_FINAL_VALUE", "width" : 12, "type" : "number" },
              { "name" : "AVM_LOW_VALUE", "width" : 12, "type" : "number" },
              { "name" : "AVM_HIGH_VALUE", "width" : 12, "type" : "number" },
              { "name" : "AVM_CONFIDENCE_SCORE", "width" : 5, "type" : "number" },
              { "name" : "AVM_FSD", "width" : 5, "type" : "number" }
            ]
  }
'''

import sys
from pprint import pprint

args = sys.argv

if len(args) < 3:
  print 'usage: python process.py <schema file> <output file> <collection name>'
  sys.exit()

csvFile = open(args[1] , 'r')
outFile = open(args[2], 'w')
collectionName = args[3]

schema = {'name' : collectionName, 'fields' : []}

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

    if name not in ['FILLER', 'CR-LF', 'Total']:
      field = {
        'name' : name,
        'width' : int(split[6]),
        'type' : 'number'
      }

      if split[3] =='varchar':
        field['type'] = 'string'
    
      schema['fields'].append(field)

pprint(schema)
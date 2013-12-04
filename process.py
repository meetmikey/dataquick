from pymongo import MongoClient
from pprint import pprint
import json

#connect to db
client = MongoClient('localhost', 27017)

db = client.dataquick

schemaFile = open('./data/avm.json' , 'r')
dataFile = open('./data/SIDEKICK_AVM_TEST.txt', 'r')

schema = schemaFile.read()

print schema
schemaJson = json.loads(schema)

pprint(schemaJson)

while True:
  line = dataFile.readline()
  if line == '':
    break
  print line
  index = 0
  for field in schemaJson['fields']:
    width = field['width']
    print field['name'] + ' ' + line[index:index+width-1]
    index+=width
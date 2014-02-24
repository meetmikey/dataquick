import json
import pprint

class DataQuickParser:

  pp = pprint.PrettyPrinter(indent=4)

  #constants
  BATCH_SIZE = 1000

  def __init__ (self, fileSchemaPath, dataPath):
    print 'init'
    print fileSchemaPath
    print dataPath

    schemaFile = open(fileSchemaPath, 'r')
    dataFile = open(dataPath, 'r')
    schema = schemaFile.read()
    self.schema = json.loads(schema)
    self.dataFile = dataFile


  def parseFile (self, batchDoneCallback):
    batch = []
    batchIndex = 0

    while True:
      print len(batch)

      line = self.dataFile.readline()

      if line == '':
        break

      lineIndex = 0
      record = {}
      print '\n' + line


      for field in self.schema['fields']:
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
              print field['name']
              self.pp.pprint(record)
              value = float(value)

        record[field['name']] = value
        lineIndex+=width

      print record

      batch.append(record)
      batchIndex += 1

      if batchIndex >= self.BATCH_SIZE:
        batchDoneCallback(batch)
        batch = []
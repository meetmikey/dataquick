import json
import pprint

class DataQuickParser:

  pp = pprint.PrettyPrinter(indent=4)

  def __init__ (self, fileSchemaPath, dataPath, batchSize):
    print fileSchemaPath
    print dataPath

    schemaFile = open(fileSchemaPath, 'r')
    dataFile = open(dataPath, 'r')
    schema = schemaFile.read()
    self.schema = json.loads(schema)
    self.dataFile = dataFile
    self.batchSize = batchSize


  def parseFile (self, batchDoneCallback):
    self.totalIndex = 0
    batch = []
    batchIndex = 0

    while True:
      line = self.dataFile.readline()

      if line == '':
        batchDoneCallback(batch)
        break

      lineIndex = 0
      record = {}

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
              value = float(value)

        if value != '':
          record[field['name']] = value

        lineIndex+=width

      batch.append(record)
      batchIndex += 1

      if batchIndex >= self.batchSize:
        self.totalIndex += batchIndex
        print 'about to callback batch from parseFile', self.totalIndex
        batchDoneCallback(batch)
        batch = []
        batchIndex = 0
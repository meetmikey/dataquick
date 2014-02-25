"""
  Takes in json schema and writes content of fixed width dataquick text file into a avro file
"""
from lib.DataQuickParser import DataQuickParser
from lib.AvroIO import AvroIO
import json
import sys

args = sys.argv


if len(args) < 5:
  print 'usage: python processAvro.py <fixed width schema file> <data file> <avro schema file> <avro output file>'
  sys.exit()

fwSchemaFile = args[1]
dataFile = args[2]
avroSchemaFile = args[3]
avroOutputFile = args[4]

avro = AvroIO(avroSchemaFile, avroOutputFile)
avro.initiateAvroFile()

def batchDoneCallback(batch):
  if batch and len(batch):
    avro.writeDatumToAvroFile(batch[0])
  else:
    print 'length of batch was 0 or undefined'

def allDoneCallback():
  print 'all done, closing file'
  avro.closeAvroFile()

myParser = DataQuickParser(fwSchemaFile, dataFile, 1)
myParser.parseFile(batchDoneCallback, allDoneCallback)
"""
  Takes in json schema and writes content of fixed width dataquick text file into a mongo collection
"""

from pymongo import MongoClient
from lib.DataQuickParser import DataQuickParser
import sys

#connect to db
client = MongoClient('localhost', 27017)

db = client.dataquick_test

args = sys.argv

if len(args) < 4:
  print 'usage: python process.py <schema file> <data file> <collection name>'
  sys.exit()

schemaFile = args[1]
dataFile = args[2]
collectionName = args[3]

mongoCollection = db[collectionName]

def batchDoneCallback(batch):
  print 'running callback'
  if batch and len(batch):
    mongoCollection.insert(batch, w=0)
  else:
    print 'length of batch was 0 or undefined'

def allDoneCallback():
  print 'all done'

myParser = DataQuickParser(schemaFile, dataFile, 1000)
myParser.parseFile(batchDoneCallback, allDoneCallback)
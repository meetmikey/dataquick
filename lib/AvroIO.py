import avro
from avro import schema, datafile, io
import json

class AvroIO:
  def __init__(self, avroSchemaPath, outFilePath):
    self.schema = avro.schema.parse(open(avroSchemaPath).read())
    self.outFile = open(outFilePath, 'wb')

  def initiateAvroFile(self):
    # Create a 'record' (datum) writer
    recWriter = io.DatumWriter(self.schema)
 
    # Create a 'data file' (avro file) writer
    dfWriter = datafile.DataFileWriter(
                    # The file to contain
                    # the records
                    self.outFile,
                    # The 'record' (datum) writer
                    recWriter,
                    # Schema, if writing a new file
                    # (aka not 'appending')
                    # (Schema is stored into
                    # the file, so not needed
                    # when you want the writer
                    # to append instead)
                    writers_schema = self.schema,
                    # An optional codec name
                    # for compression
                    # ('null' for none)
                    codec = 'deflate'
                )
 

    self.dfWriter = dfWriter
 
  def writeDatumToAvroFile(self, data):
    # Write our data
    # (You can call append multiple times
    # to write more than one record, of course)
    self.dfWriter.append(data)

  def closeAvroFile(self):
    # Close to ensure writing is complete
    self.dfWriter.close()
  
  def readAvroFile(self, fileName):
    # Create a 'record' (datum) reader
    # You can pass an 'expected=SCHEMA' kwarg
    # if you want it to expect a particular
    # schema (Strict)
    recReader = io.DatumReader()
 
    # Create a 'data file' (avro file) reader
    dfReader = datafile.DataFileReader(open(fileName), recReader)
 
    # Read all records stored inside
    for record in df_reader:
      print record
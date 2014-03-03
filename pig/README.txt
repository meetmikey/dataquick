Collection of pig scripts to transform + work with dataquick data. Each has a configurable parameter file by the same name as the script groupByProperty.params. Tested with pig version 0.12.0.

To run:

pig -x <local | mapreduce> -m <script_name>.param <script_name>.pig

groupByProperty.pig - takes as input a directory of .avro files from county recorder office data (i.e. transaction data) and outputs the transactions grouped by property id (SR_PROPERTY_ID)

joinAssessment.pig - takes as input the .avro output of groupByProperty.pig and joins .avro tax assessment data (join SR_PROPERTY_ID from recorder office to SA_PROPERTY_ID from assessment data)

getTransactionsByYearAndMuni.pig - takes as input a directory of .avro files from county recorder office data (i.e. transaction data) and outputs aggregate statsistics on transactions by municipality and year.
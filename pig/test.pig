REGISTER lib/avro-1.7.6.jar
REGISTER lib/piggybank.jar

assessments = LOAD './assessor.avro'
USING AvroStorage ();

groups = GROUP assessments BY SA_SITE_ZIP;
sc = FOREACH groups GENERATE group AS SA_SITE_ZIP, COUNT(assessments) AS cnt;

STORE sc INTO 'outfile' using PigStorage();

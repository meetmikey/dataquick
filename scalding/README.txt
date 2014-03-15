scalding scripts for processing data

```
Use fixedwidth only
Combine the SIDEKICK_HISTORY*.TXT files into 1
cat SIDEKICK_HISTORY_* > SIDEKICK_HISTORY.txt

Then run Scalding locally like so -
scalding/scripts/scald.rb --hdfs-local HistoryJob.scala --history fixedwidth/SIDEKICK_HISTORY.txt --assessor fixedwidth/SIDEKICK_ASSESSOR.TXT --output joined

Inner-join history & assessor based on common pkey, write to disk.
Use selected features.
```

scalding scripts for processing data

```
cat SIDEKICK_HISTORY_* > SIDEKICK_HISTORY.txt
scalding/scripts/scald.rb --hdfs-local HistoryJob.scala --history fixedwidth/SIDEKICK_HISTORY.txt --assessor fixedwidth/SIDEKICK_ASSESSOR.TXT --output joined
```

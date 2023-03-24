# BootCounter
Simple boot counter implemented for a testing task purpose

TODO: 
Add Realm implementation
Improve UI

For the same of simplisity was used JobScheduler.
Possible drawback of usage JobScheduler is unreliable work when app is in background. 
For 100% sure I would suggest using AlarmManager, but current task hasn't required such.

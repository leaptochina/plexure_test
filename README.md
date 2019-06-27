# plexure_test
for PLEXURE interview




Runing Environment:
  Mobile >= Android 4.1
  Has Internet / GPS model

PS:
  When user's location changes, the list will refresh automatically
  I put the distance large than 8600km cannot add to fav, you can modify this config on configs/Configs.kt
  I cannot understand sort by featurelist property, this sorting basis is fuzzy.


I choose the top 4 features, they are
  (Data handling) Introduce a filter feature. This feature postprocesses the received list and sorts the stores by their featureList property. It's would be a dialog with the list of feature List and when one feature got selected, the store list will be updated only showing the stores with the sleected feature. the dialog will have also All choice.
  (Data handling) Introduce a filter feature. This feature postprocesses the received list and sorts the stores by their distance property deciding and assending. It's would be a button on top bar to filter deciding/assending/none.
  (Data handling) Introduce a validation feature. This feature postprocesses the recieved list and grays out all far stores. A store is far when the the distance is more than 1000 KM. Prevent users from adding far store to the favorites list.
  (Multithreading) Show the Address property of each store, but with a 2 seconds delay. Use the sleep() function (to simulate a heavy computing). The app should be still responsive all the time.









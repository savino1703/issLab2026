%====================================================================================
% firefly_sensor description   
%====================================================================================
dispatch( cellstate, cellstate(X,Y,COLOR) ).
event( sonar_data, distance(D) ). %Distanza rilevata dal sonar
event( startSync, startSync(1) ). %Passa a modalità sincrona
event( stopSync, stopSync(1) ). %Torna a modalità random
event( sync, sync(1) ). %Segnale di clock globale
%====================================================================================
context(ctxfirefly, "localhost",  "TCP", "8040").
context(ctxgrid, "127.0.0.1",  "TCP", "8050").
 qactor( griddisplay, ctxgrid, "external").
  qactor( sonarsimulator, ctxfirefly, "it.unibo.sonarsimulator.Sonarsimulator").
 static(sonarsimulator).
  qactor( coordinator, ctxfirefly, "it.unibo.coordinator.Coordinator").
 static(coordinator).
  qactor( firefly1, ctxfirefly, "it.unibo.firefly1.Firefly1").
 static(firefly1).
  qactor( firefly2, ctxfirefly, "it.unibo.firefly2.Firefly2").
 static(firefly2).
  qactor( firefly3, ctxfirefly, "it.unibo.firefly3.Firefly3").
 static(firefly3).

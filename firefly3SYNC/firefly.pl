%====================================================================================
% firefly description   
%====================================================================================
dispatch( cellstate, cellstate(X,Y,COLOR) ). %commute cell state
event( sync, sync(V) ). %evento di sincronizzazione globale
%====================================================================================
context(ctxfirefly, "localhost",  "TCP", "8040").
context(ctxgrid, "192.168.1.77",  "TCP", "8050").
 qactor( griddisplay, ctxgrid, "external").
  qactor( coordinator, ctxfirefly, "it.unibo.coordinator.Coordinator").
 static(coordinator).
  qactor( firefly1, ctxfirefly, "it.unibo.firefly1.Firefly1").
 static(firefly1).
  qactor( firefly2, ctxfirefly, "it.unibo.firefly2.Firefly2").
 static(firefly2).
  qactor( firefly3, ctxfirefly, "it.unibo.firefly3.Firefly3").
 static(firefly3).

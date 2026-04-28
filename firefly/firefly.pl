%====================================================================================
% firefly description   
%====================================================================================
dispatch( cellstate, cellstate(X,Y,COLOR) ). %da firefly1 a griddisplaymock
%====================================================================================
context(ctxfirefly, "localhost",  "TCP", "8080").
context(ctxgrid, "127.0.0.1",  "TCP", "8050").
 qactor( griddisplay, ctxgrid, "external").
  qactor( firefly, ctxfirefly, "it.unibo.firefly.Firefly").
 static(firefly).

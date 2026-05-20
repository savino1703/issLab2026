%====================================================================================
% boundarysystem description   
%====================================================================================
dispatch( start, start(ARG) ).
dispatch( cmd, cmd(MOVE) ).
event( wall, wall(ARG) ).
event( radar, distance(D) ).
%====================================================================================
context(ctxboundary, "localhost",  "TCP", "8015").
context(ctxrobot, "127.0.0.1",  "TCP", "8020").
 qactor( baserobot, ctxrobot, "external").
  qactor( boundaryworker, ctxboundary, "it.unibo.boundaryworker.Boundaryworker").
 static(boundaryworker).

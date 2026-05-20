%====================================================================================
% ddrboundary description   
%====================================================================================
dispatch( move, move(M) ).
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
dispatch( start, start(ARG) ).
event( radar, distance(D) ).
%====================================================================================
context(ctxboundary, "localhost",  "TCP", "8120").
context(ctxrobotservice26, "127.0.0.1",  "TCP", "8125").
 qactor( robotactor, ctxrobotservice26, "external").
  qactor( boundaryworker, ctxboundary, "it.unibo.boundaryworker.Boundaryworker").
 static(boundaryworker).

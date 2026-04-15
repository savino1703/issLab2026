%====================================================================================
% conway26demoqak description   
%====================================================================================
mqttBroker("localhost", "1833", "lifegameIn").
event( start, start(x) ).
%====================================================================================
context(ctxgame, "localhost",  "TCP", "8010").
 qactor( lifegame, ctxgame, "it.unibo.lifegame.Lifegame").
 static(lifegame).

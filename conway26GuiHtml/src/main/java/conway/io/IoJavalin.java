 package conway.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import unibo.basicomm23.utils.CommUtils;

public class IoJavalin {
    private boolean isStarted = false; // Il gioco parte "fermo"
        private domain.LifeController lifeController;
	
	public IoJavalin() {
        // Inizializza LifeController (mock: da sistemare con istanze reali)
        lifeController = new domain.LifeController(
            new domain.Life(10, 10), // dimensioni esempio
            new devices.MockOutdev() // istanza corretta
        );
        var app = Javalin.create(config -> {
			config.staticFiles.add(staticFiles -> {
				staticFiles.directory = "/page";
				staticFiles.location = Location.CLASSPATH; // Cerca dentro il JAR/Classpath
				/*
				 * i file sono "impacchettati" con il codice, non cercati sul disco rigido esterno.
				 */
		    });
		}).start(8080);
        
        app.get("/", ctx -> {
            Path path = Path.of("/conway26GuiHtml/src/main/resources/page/ConwayInOutPage.html");   
		    if (Files.exists(path)) {
		        // Usiamo Files.newInputStream che è più moderno di FileInputStream
		        ctx.contentType("text/html").result(Files.newInputStream(path));
		    } else {
		        ctx.status(404).result("File non trovato nel file system");
		    }
		    //ctx.result("Hello from Java!"));  //la forma più semplice di risposta
        }); 
        
        app.get("/greet/{name}", ctx -> {
            String name = ctx.pathParam("name");
            ctx.result("Hello, " + name + "!");
        }); //http://localhost:7070/greet/Alice
        
        app.get("/api/users", ctx -> {
            Map<String, Object> user = Map.of("id", 1, "name", "Bob");
            ctx.json(user); // Auto-converts to JSON
        });
        
        /*
         * Javalin v5+): Si passa solo la "promessa" (il Supplier del Future). 
         * Javalin è diventato più intelligente: se il Future restituisce una Stringa, 
         * lui fa ctx.result(stringa). Se restituisce un oggetto, lui fa ctx.json(oggetto).
         * 
         */
        app.get("/async", ctx -> {
        	ctx.future(() -> {
	        	// Creiamo il future
	            CompletableFuture<String> future = new CompletableFuture<>();
	            
	            // Eseguiamo il lavoro in un altro thread
	            new Thread(() -> { 
	                try {
	                    Thread.sleep(2000); // Simulazione calcolo pesante
	                    future.complete("Risultato calcolato asincronamente");
	                } catch (Exception e) {
	                    future.completeExceptionally(e);
	                }
	            });
	            
	            return future; // Restituiamo il future a Javalin
        	});
        });
        
        app.get("/async1", ctx -> {
            ctx.future(() -> CompletableFuture.supplyAsync(() -> {
                // Simuliamo l'operazione lenta
                try {
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Risultato calcolato con supplyAsync";
            }));
        });
        
        app.ws("/chat", ws -> {
            ws.onMessage(ctx -> {
                String msg = ctx.message();
                if (msg.contains("start")) {
                    isStarted = true;
                    lifeController.onStart();
                    ctx.send("Gioco avviato: ora puoi interagire con la griglia.");
                } else if (msg.contains("stop")) {
                    isStarted = false;
                    lifeController.onStop();
                    ctx.send("Gioco fermato: interazione disabilitata.");
                } else if (msg.contains("clear")) {
                    lifeController.onClear();
                    ctx.send("Griglia pulita.");
                } else if (msg.contains("cell(")) {
                    if (isStarted) {
                        // Estrai coordinate cell(x,y)
                        String cellCmd = msg.substring(msg.indexOf("cell("), msg.lastIndexOf(")") + 1);
                        String[] parts = cellCmd.replace("cell(","").replace(")","").split(",");
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        lifeController.switchCellState(x, y);
                        ctx.send("cell("+x+","+y+",1)"); // invia stato aggiornato
                    } else {
                        ctx.send("Azioni bloccate: premi START per abilitare la griglia.");
                    }
                }
            });
        });
	}
	
 
	

	
	public static void main(String[] args) {
		var resource = IoJavalin.class.getResource("/pages");
		CommUtils.outgreen("DEBUG: La cartella /page si trova in: " + resource);
		new IoJavalin();
	}

}

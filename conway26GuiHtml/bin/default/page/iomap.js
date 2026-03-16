/*
iomap.js
*/
const ncell = 20;  //WARNING change in mapstyle.css

const mapContainer = document.getElementById("map");  
mapContainer.innerHTML = '';
    
 	
function createMapRep(){
 for( let i=0; i<ncell; i++ ){
     for( let j=0; j<ncell; j++ ){
        const cellElement = document.createElement("div");
        cellElement.classList.add("cell");
        cellElement.classList.add("live");
        cellElement.id = `cell(${i},${j})`;
        cellElement.addEventListener('click', function() {
            sendCmdToServer(cellElement.id);  //cell(i,j) 
            //alert(""+cellElement.id );
        });
        mapContainer.appendChild(cellElement);
        //console.log("created cell", cellElement.id);
}//for
}//for
}//createMapRep


function updateCellColor(newX, newY, color) {
    // Usiamo newX e newY nell'ordine corretto in cui sono stati creati (i, j)
    const cellxy = document.getElementById(`cell(${newX},${newY})`);
    
    if (!cellxy) {
        console.error("Cella non trovata:", newX, newY);
        return;
    }

    console.log("Updating cell", newX, newY, "to state", color);

    // Rimuoviamo gli stati precedenti
    cellxy.classList.remove("live", "dead");

    if (color == "1") {
        cellxy.classList.add("dead"); // Cella attiva
    } else {
        cellxy.classList.add("live"); // Cella vuota
    }
}
    
console.log("iomap.js loaded " + map);    
createMapRep()

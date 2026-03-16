package conway.domain;

public interface ICell {
	void setStatus(boolean v); // Qualunque cosa sia una cella, esiste solo la rappresentazione della cella
	boolean isAlive(); // La cella come entità ha la capacità di rispondere ad una query, che mi restituisce un valore, ossia vivo o morto.
	void switchCellState(); // La cella è un ente che ha la capacità di, attraverso questo metodo, cambiare stato.
}

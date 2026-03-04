package src.main.java.conway.domain;

public class Grid implements IGrid{
	int nr;
	int nc;
	Cell[][] cells;
	
	public Grid(int nr, int nc) {
		this.nr=nr;
		this.nc=nc;
		cells= new Cell[nr][nc];
	}

	public ICell getCell(int x, int y) {
		return this.cells[x][y];	
	}
	
}

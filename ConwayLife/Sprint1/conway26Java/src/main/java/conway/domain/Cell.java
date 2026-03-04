package src.main.java.conway.domain;

public class Cell implements ICell{
	private int value;

	@Override
	public void setStatus(boolean v) {
		// TODO Auto-generated method stub
		if(v) value=1;
		else value=-1;
	}

	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlive() {
		return value > 0; //Ritorna true se value è maggiore di 0, false altrimenti
	}
	
}

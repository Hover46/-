package model;
import java.io.Serializable;

public class ChessPiece implements Observer, Serializable{
    protected PieceType type;
    protected boolean isRed;
    private int row;
    private int col;
    
    private Dataset dataset;
    
    protected MovementChecking movementChecking;
    
    public ChessPiece(boolean isRed,Subject subject) {
        this.isRed = isRed;
        subject.registerObserver(this);
    }
    
    public PieceType getType() {
        return type;
    }
    
    public boolean isRed() {
        return isRed;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

	public void update(Dataset dataset) {
		this.dataset = dataset;
	}
	
	public Boolean isValidMove(){
		return(movementChecking.isValidMove(this.dataset));
	}
}

package model;
import java.io.Serializable;

public class Dataset implements Serializable{
	private int nextRow;
	private int nextCol;
	private int thisRow;
	private int thisCol;
	private Boolean isRed;
	private ChessPiece[][] chessboard;
	
	public Dataset(int thisRow,int thisCol,int nextRow, int nextCol, Boolean isRed, ChessPiece[][] chessboard) {
		this.thisRow = thisRow;
		this.thisCol = thisCol;
		this.nextRow = nextRow;
		this.nextCol = nextCol;
		this.isRed = isRed;
		this.chessboard = chessboard;
	}
	
	public int getThisRow() {
		return thisRow;
	}
	public int getThisCol() {
		return thisCol;
	}
	public int getNextRow() {
		return nextRow;
	}
	public int getNextCol() {
		return nextCol;
	}
	public Boolean isRed() {
		return isRed;
	}
	public ChessPiece[][] getChessboard(){
		return chessboard;
	}
}

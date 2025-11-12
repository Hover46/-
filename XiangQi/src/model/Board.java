package model;

public interface Board {
	public int getBoardWidth();

	public int getBoardHeight();

	public int getCellSize();

	public ChessPiece getSelectedPiece();

	public int getRowOfSelectedPiece();

	public int getColOfSelectedPiece();

	public ChessPiece[][] getBoard();
	
	public void moveChess(int row, int col);

	public Boolean isGameOver();
	
	public String getDisplayName(int row, int col);
}

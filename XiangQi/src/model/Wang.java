package model;

public class Wang extends ChessPiece{
	
	public Wang(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.KING;
		movementChecking = new WangChecking();
	}
	
}

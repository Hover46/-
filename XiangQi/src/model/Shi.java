package model;

public class Shi extends ChessPiece{

	public Shi(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.GUARD;
		movementChecking = new ShiChecking();
	}
	
}

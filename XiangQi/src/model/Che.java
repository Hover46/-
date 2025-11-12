package model;

public class Che extends ChessPiece{

	public Che(boolean isRed,Subject subject) {
		super(isRed, subject);
		this.type = PieceType.ROOK;
		movementChecking = new CheChecking();
	}
	
}

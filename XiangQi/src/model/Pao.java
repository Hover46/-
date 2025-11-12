package model;

public class Pao extends ChessPiece{

	public Pao(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.CANNON;
		movementChecking = new PaoChecking();
	}

}

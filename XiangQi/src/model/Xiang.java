package model;

public class Xiang extends ChessPiece{

	public Xiang(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.ELEPHANT;
		movementChecking = new XiangChecking();
	}
	
}

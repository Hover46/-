package model;

public class Ma extends ChessPiece{

	public Ma(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.HORSE;
		movementChecking = new MaChecking();
	}
	
}

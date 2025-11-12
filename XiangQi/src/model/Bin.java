package model;

public class Bin extends ChessPiece{

	public Bin(boolean isRed, Subject subject) {
		super(isRed, subject);
		this.type = PieceType.SOLDIER;//多做一个Bin类而不直接用BinChecking的意义
		movementChecking = new BinChecking();//不做装饰者模式了，象棋的逻辑太复杂了，太难拆解逻辑了。
	}

}

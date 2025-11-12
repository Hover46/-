package model;

public interface ChessFactory {
	//其实没什么必要做，棋子初始化基本不用变，并且不用“选择”棋子来初始化。（暂时）这里仅作学习作用
	public ChessPiece createChess(String type, Boolean isRed, Subject subject);
	
}

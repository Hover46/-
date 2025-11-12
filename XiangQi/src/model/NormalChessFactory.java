package model;
import java.io.Serializable;

public class NormalChessFactory implements ChessFactory, Serializable {

	public ChessPiece createChess(String type, Boolean isRed, Subject subject) {
		ChessPiece chess = null;
		if (type == "Che") {
			chess = new Che(isRed,subject);
		}
		if (type == "Ma") {
			chess = new Ma(isRed,subject);
		}
		if (type == "Xiang") {
			chess = new Xiang(isRed,subject);
		}
		if (type == "Shi") {
			chess = new Shi(isRed,subject);
		}
		if (type == "Wang") {
			chess = new Wang(isRed,subject);
		}
		if (type == "Pao") {
			chess = new Pao(isRed,subject);
		}
		if (type == "Bin") {
			chess = new Bin(isRed,subject);
		}
		// 简单检查
		if (chess == null) {
			System.out.println("不存在该棋子类型："+type);
		}
		return chess;
	}
}

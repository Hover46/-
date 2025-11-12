package model;
import java.io.Serializable;

public class WangChecking implements MovementChecking, Serializable {

	private ShiChecking nineSquareGridChecking = new ShiChecking();// 复用检查九宫格的代码
	private CheChecking numberOfChessOnRoadChecking = new CheChecking();// 复用检查路上棋子数量的代码
	// 这里不能用多态

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		if (dataset.getThisRow() == dataset.getNextRow()) {
			// 检查是否在九宫格内
			if (nineSquareGridChecking.isInNineSquareGrid(dataset)) {// 这里不能用多态的原因
				// 检查移动一格
				if (Math.abs(dataset.getThisCol() - dataset.getNextCol()) == 1) {
					isValidMove = true;
				}
			}
		}
		if (dataset.getThisCol() == dataset.getNextCol()) {
			// 王不见王,这里的逻辑是王可以见王，在这种情况下王可以杀王，默认王一见面就相杀。
			if(dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()] != null) {
				if (numberOfChessOnRoadChecking.numberOfChessOnCol(dataset) == 0
						&& dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()].getType().equals(PieceType.KING)) {
					isValidMove = true;
				}
			}
			// 检查是否在九宫格内
			if (nineSquareGridChecking.isInNineSquareGrid(dataset)) {
				// 检查移动一格
				if (Math.abs(dataset.getThisRow() - dataset.getNextRow()) == 1) {
					isValidMove = true;
				}
			}
		}

		return isValidMove;
	}

}

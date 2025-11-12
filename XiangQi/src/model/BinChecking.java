package model;
import java.io.Serializable;

public class BinChecking implements MovementChecking, Serializable{

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		// 区分红黑
		if (dataset.isRed()) {
			// 当同行移动时,检查是否过河
			if (dataset.getThisRow() == dataset.getNextRow() && !isInMyField(dataset, dataset.getThisRow())) {
				isValidMove = true;
				// 检查向左右移动一格
				if (Math.abs(dataset.getThisCol() - dataset.getNextCol()) != 1) {
					isValidMove = false;
				}
			}
			if (dataset.getThisCol() == dataset.getNextCol()) {
				isValidMove = true;
				// 检查向前移动一格
				if ((dataset.getNextRow() - dataset.getThisRow()) != -1) {
					isValidMove = false;
				}
			}
		} else {
			// 当同行移动时,检查是否过河
			if (dataset.getThisRow() == dataset.getNextRow() && !isInMyField(dataset, dataset.getThisRow())) {
				isValidMove = true;
				// 检查向左右移动一格
				if (Math.abs(dataset.getThisCol() - dataset.getNextCol()) != 1) {
					isValidMove = false;
				}
			}
			if (dataset.getThisCol() == dataset.getNextCol()) {
				isValidMove = true;
				// 检查向前移动一格
				if ((dataset.getNextRow() - dataset.getThisRow()) != 1) {
					isValidMove = false;
				}
			}
		}
		return isValidMove;
	}

	public boolean isInMyField(Dataset dataset, int row) {
		Boolean isValidMove = false;
		if (dataset.isRed()) {
			isValidMove = row >= 5;// 变量可与棋盘大小绑定，这里不做。
		} else {
			isValidMove = row <= 4;
		}
		return isValidMove;
	}

}

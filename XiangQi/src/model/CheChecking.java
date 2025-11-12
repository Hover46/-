package model;
import java.io.Serializable;

public class CheChecking implements MovementChecking, Serializable {

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		// 判断是否走直线
		if (dataset.getThisRow() == dataset.getNextRow()) {
			isValidMove = true;
			// 判断中间有没有东西挡着
			if (numberOfChessOnCol(dataset) != 0) {
				isValidMove = false;
			}
			// 两个判断可以合在一起。
		}
		if (dataset.getThisCol() == dataset.getNextCol()) {
			isValidMove = true;
			// 判断中间有没有东西挡着
			if (numberOfChessOnRow(dataset) != 0) {
				isValidMove = false;
			}
		}
		return (isValidMove);
	}

	// 单独列出来以实现代码复用
	public int numberOfChessOnCol(Dataset dataset) {
		int numberOfChessOnCol = 0;

		if (dataset.getThisCol() < dataset.getNextCol()) {
			for (int i = dataset.getThisCol() + 1; i < dataset.getNextCol(); i++) {
				if (dataset.getChessboard()[dataset.getThisRow()][i] != null) {
					numberOfChessOnCol++;
				}
			}
		} else {
			for (int i = dataset.getThisCol() - 1; i > dataset.getNextCol(); i--) {
				System.out.println(dataset.getChessboard()[dataset.getThisRow()][i]);
				if (dataset.getChessboard()[dataset.getThisRow()][i] != null) {
					numberOfChessOnCol++;
				}
			}
		}
		return numberOfChessOnCol;
	}

	public int numberOfChessOnRow(Dataset dataset) {
		int numberOfChessOnRow = 0;

		if (dataset.getThisRow() < dataset.getNextRow()) {
			for (int i = dataset.getThisRow() + 1; i < dataset.getNextRow(); i++) {
				if (dataset.getChessboard()[i][dataset.getThisCol()] != null) {
					numberOfChessOnRow++;
				}
			}
		} else {
			for (int i = dataset.getThisRow() - 1; i > dataset.getNextRow(); i--) {
				if (dataset.getChessboard()[i][dataset.getThisCol()] != null) {
					numberOfChessOnRow++;
				}
			}
		}
		return numberOfChessOnRow;
	}

}

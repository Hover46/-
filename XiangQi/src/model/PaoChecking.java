package model;
import java.io.Serializable;

public class PaoChecking implements MovementChecking, Serializable{
	
	private CheChecking basicChecking = new CheChecking();//包裹CheChecking 复用numberOfChessOnCol，numberOfChessOnRow
	//名字起得不好
	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		if (dataset.getThisRow() == dataset.getNextRow()) {
			isValidMove = true;
			// 判断中间是不是只有一个且一定会吃或没有东西挡着但不能吃
			if((basicChecking.numberOfChessOnCol(dataset) != 1 || dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()] == null) && !(basicChecking.numberOfChessOnCol(dataset) == 0 && dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()] == null)) {
				isValidMove = false;
			}
		}
		if (dataset.getThisCol() == dataset.getNextCol()) {
			isValidMove = true;
			// 判断中间是不是只有一个且一定会吃或没有东西挡着但不能吃
			if((basicChecking.numberOfChessOnRow(dataset) != 1 || dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()] == null) && !(basicChecking.numberOfChessOnRow(dataset) == 0 && dataset.getChessboard()[dataset.getNextRow()][dataset.getNextCol()] == null)) {
				isValidMove = false;
			}
		}
		return(isValidMove);
	}
	
}

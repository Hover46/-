package model;
import java.io.Serializable;

public class ShiChecking implements MovementChecking, Serializable{

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		//斜着走格子
		if(Math.abs(dataset.getNextRow() - dataset.getThisRow()) == 1 && Math.abs(dataset.getNextCol() - dataset.getThisCol()) == 1) {
			//在九宫格内
			isValidMove = isInNineSquareGrid(dataset);
		}
		return isValidMove;
	}
	
	public Boolean isInNineSquareGrid(Dataset dataset) {//默认判断下个位置，应为基本不会有判断这个位置的必要
		Boolean isValidMove = false;
		if(dataset.getNextCol() >= 3 && dataset.getNextCol() <= 5) {
			if(dataset.isRed()) {
				isValidMove = dataset.getNextRow() >= 7 && dataset.getNextRow() <= 9;
			}else {
				isValidMove = dataset.getNextRow() >= 0 && dataset.getNextRow() <= 2;
			}
		}
		return isValidMove;
	}
}

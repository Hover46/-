package model;
import java.io.Serializable;

public class MaChecking implements MovementChecking, Serializable{

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		//向不同方向走日，哪个方向对应哪个方向？
		isValidMove = isMoveWithoutObstacle(dataset, 2, 1) || isMoveWithoutObstacle(dataset, 2, -1) || isMoveWithoutObstacle(dataset, -2, 1) || isMoveWithoutObstacle(dataset, -2, -1) || isMoveWithoutObstacle(dataset, 1, 2) || isMoveWithoutObstacle(dataset, 1, -2) || isMoveWithoutObstacle(dataset, -1, 2) || isMoveWithoutObstacle(dataset, -1, -2);
		return isValidMove;
	}
	
	public Boolean isMoveWithoutObstacle(Dataset dataset,int rowToMove,int colToMove) {
		Boolean isValidMove = false;
		if((dataset.getNextRow() - dataset.getThisRow()) == rowToMove && (dataset.getNextCol() - dataset.getThisCol()) == colToMove) {
			isValidMove = true;
			//绊腿
			if(rowToMove > 0) {
				rowToMove = rowToMove - 1;
			}else {
				rowToMove = rowToMove + 1;
			}
			if(colToMove > 0) {
				colToMove = colToMove - 1;
			}else {
				colToMove = colToMove + 1;
			}
			if(dataset.getChessboard()[dataset.getThisRow()+rowToMove][dataset.getThisCol()+colToMove] != null) {
				isValidMove = false;
			}
		}
		return isValidMove;
	}

}

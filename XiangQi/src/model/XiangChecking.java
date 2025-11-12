package model;
import java.io.Serializable;

public class XiangChecking implements MovementChecking, Serializable{
	
	private MaChecking obstacleChecking = new MaChecking();//对马的别脚代码复用
	private BinChecking fieldChecking = new BinChecking();//对兵的判断半场代码复用

	public Boolean isValidMove(Dataset dataset) {
		Boolean isValidMove = false;
		//向不同方向走田
		isValidMove = obstacleChecking.isMoveWithoutObstacle(dataset, 2, 2) || obstacleChecking.isMoveWithoutObstacle(dataset, 2, -2) || obstacleChecking.isMoveWithoutObstacle(dataset, -2, 2) || obstacleChecking.isMoveWithoutObstacle(dataset, -2, -2);
	    //如果不在自己半场
		//半场的条件可以再提公因式(其实因为场地大小基本不会变，提不提没什么关系）
		//略生硬
		if(!fieldChecking.isInMyField(dataset,dataset.getNextRow())) {
			isValidMove = false;
		}
		return isValidMove;
	}
	
}

package model;
import java.io.Serializable;
import java.util.ArrayList;

public class ChessBoard implements Subject, Board ,Data, Serializable{
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private int CELL_SIZE;

	private ChessPiece[][] board;
	private boolean redTurn = true; // 红方先行
	private ChessPiece selectedPiece = null;

	private ArrayList<Observer> observers = new ArrayList<Observer>();;// 收集观察者

	Boolean isGameOver = false;
	
	private ChessFactory chessFactory;
	
	public ChessBoard() {
		BOARD_WIDTH = 9;
		BOARD_HEIGHT = 10;
		CELL_SIZE = 60;

		board = new ChessPiece[BOARD_HEIGHT][BOARD_WIDTH];

		// 指定工厂
		chessFactory = new NormalChessFactory();

		// 初始化红方棋子
		board[9][0] = chessFactory.createChess("Che", true, this);
		board[9][1] = chessFactory.createChess("Ma", true, this);
		board[9][2] = chessFactory.createChess("Xiang", true, this);
		board[9][3] = chessFactory.createChess("Shi", true, this);
		board[9][4] = chessFactory.createChess("Wang", true, this);
		board[9][5] = chessFactory.createChess("Shi", true, this);
		board[9][6] = chessFactory.createChess("Xiang", true, this);
		board[9][7] = chessFactory.createChess("Ma", true, this);
		board[9][8] = chessFactory.createChess("Che", true, this);
		board[7][1] = chessFactory.createChess("Pao", true, this);
		board[7][7] = chessFactory.createChess("Pao", true, this);
		board[6][0] = chessFactory.createChess("Bin", true, this);
		board[6][2] = chessFactory.createChess("Bin", true, this);
		board[6][4] = chessFactory.createChess("Bin", true, this);
		board[6][6] = chessFactory.createChess("Bin", true, this);
		board[6][8] = chessFactory.createChess("Bin", true, this);

		// 初始化黑方棋子
		board[0][0] = chessFactory.createChess("Che", false, this);
		board[0][1] = chessFactory.createChess("Ma", false, this);
		board[0][2] = chessFactory.createChess("Xiang", false, this);
		board[0][3] = chessFactory.createChess("Shi", false, this);
		board[0][4] = chessFactory.createChess("Wang", false, this);
		board[0][5] = chessFactory.createChess("Shi", false, this);
		board[0][6] = chessFactory.createChess("Xiang", false, this);
		board[0][7] = chessFactory.createChess("Ma", false, this);
		board[0][8] = chessFactory.createChess("Che", false, this);
		board[2][1] = chessFactory.createChess("Pao", false, this);
		board[2][7] = chessFactory.createChess("Pao", false, this);
		board[3][0] = chessFactory.createChess("Bin", false, this);
		board[3][2] = chessFactory.createChess("Bin", false, this);
		board[3][4] = chessFactory.createChess("Bin", false, this);
		board[3][6] = chessFactory.createChess("Bin", false, this);
		board[3][8] = chessFactory.createChess("Bin", false, this);

	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void moveChess(int row, int col) {
		ChessPiece piece = board[row][col];
		// 如果已经选中了棋子
		if (selectedPiece != null) {
			// 尝试移动棋子
			for (int i = 0; i < observers.size(); i++) {
				Observer observer = observers.get(i);
				observer.update(new Dataset(selectedPiece.getRow(), selectedPiece.getCol(), row, col, redTurn, board));// 这次用推的方法
			}
			if (isValidMove(selectedPiece, row, col) && selectedPiece.isValidMove()) {
				// 判断是否是将或帅
				if(board[row][col] != null) {
					if(board[row][col].getType().equals(PieceType.KING)) {
						isGameOver = true;
					}
				}
				// 移动棋子
				board[row][col] = selectedPiece;
				board[selectedPiece.getRow()][selectedPiece.getCol()] = null;
				selectedPiece = null;
				redTurn = !redTurn; // 切换回合
			} else {
				// 无效移动，取消选择
				selectedPiece = null;
			}
		} else {
			// 选择棋子
			// 这个判断标准绝对不会变，就不单独做类了（再说吧）
			if (piece != null && piece.isRed() == redTurn) {
				selectedPiece = piece;
				selectedPiece.setPosition(row, col);
			}
		}
	}

	// 检查移动是否有效
	// 这个判断标准绝对不会变，就不单独做类了
	private boolean isValidMove(ChessPiece piece, int toRow, int toCol) {
		// 不能移动到己方棋子的位置
		if (board[toRow][toCol] != null && board[toRow][toCol].isRed() == piece.isRed()) {
			return false;
		}
		return true;
	}

	public int getBoardWidth() {
		return BOARD_WIDTH;
	}

	public int getBoardHeight() {
		return BOARD_HEIGHT;
	}

	public int getCellSize() {
		return CELL_SIZE;
	}

	public ChessPiece getSelectedPiece() {
		return selectedPiece;
	}

	public int getRowOfSelectedPiece() {
		return selectedPiece.getRow();
	}

	public int getColOfSelectedPiece() {
		return selectedPiece.getCol();
	}

	public ChessPiece[][] getBoard() {
		return board;
	}
	
	public Boolean isGameOver() {
		return isGameOver;
	}

	public String getDisplayName(int row, int col) {
		return board[row][col].getType().getDisplayName(board[row][col].isRed());
	}
}
//棋子类型枚举
enum PieceType {
	KING("将", "帅"), GUARD("士", "仕"), ELEPHANT("象", "相"), HORSE("马", "马"), ROOK("车", "车"), CANNON("炮", "炮"),
	SOLDIER("卒", "兵");

	private final String blackName;
	private final String redName;

	PieceType(String blackName, String redName) {
		this.blackName = blackName;
		this.redName = redName;
	}

	public String getDisplayName(boolean isRed) {
		return isRed ? redName : blackName;
	}
}

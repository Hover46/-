package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Board;
import model.ChessBoard;
import model.Data;
import model.UserData;

public class GamePanel extends JPanel implements PageSwitchSubject {
	private Board board;
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private int CELL_SIZE;

	private PageSwitchListener listener;

	public GamePanel() {
		initializeBoard();

		setPreferredSize(new Dimension((BOARD_WIDTH + 1) * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
		setBackground(new Color(220, 179, 92)); // 棋盘背景色

		// 添加鼠标监听器
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int col = e.getX() / CELL_SIZE;
				int row = e.getY() / CELL_SIZE;

				if (col >= 0 && col < BOARD_WIDTH && row >= 0 && row < BOARD_HEIGHT) {
					handleClick(row, col);
				}
			}
		});
	}

	// 初始化棋盘
	private void initializeBoard() {
		board = new ChessBoard();
		BOARD_WIDTH = board.getBoardWidth();
		BOARD_HEIGHT = board.getBoardHeight();
		CELL_SIZE = board.getCellSize();

		// 创建按钮面板
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false); // 透明背景
		buttonPanel.setLayout(new GridLayout(2, 1, 0, 20));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(350, 735, 200, 200));

		// 创建存档按钮
		JButton saveButton = createStyledButton("<html>存<br>档</html>");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveManager.saveData((Data)board, "GameData" + UserData.getInstance().getUserInGame());
				// 异常处理未解决，抛异常后不能下棋。
			}
		});

		
		// 创建读档按钮
		JButton readButton = createStyledButton("<html>读<br>档</html>");
		readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.board = (Board)SaveManager.loadData("GameData" + UserData.getInstance().getUserInGame());
				repaint();
			}
		});

		buttonPanel.add(saveButton);
		buttonPanel.add(readButton);

		add(buttonPanel, BorderLayout.CENTER);
	}

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("微软雅黑", Font.BOLD, 24));
		button.setBackground(new Color(70, 130, 180)); 
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setPreferredSize(new Dimension(50, 100));

		// 添加鼠标悬停效果
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(100, 149, 237)); 
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(70, 130, 180)); // 恢复原色
			}
		});

		return button;
	}

	// 处理点击事件
	private void handleClick(int row, int col) {
		board.moveChess(row, col);
		repaint();
		if (board.isGameOver()) {
			listener.switchToPage("EndMenu");
			// 初始化
			board = new ChessBoard();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 绘制棋盘
		drawBoard(g);

		// 绘制棋子
		drawPieces(g);

		// 绘制选中的棋子
		if (board.getSelectedPiece() != null) {
			int col = board.getColOfSelectedPiece();
			int row = board.getRowOfSelectedPiece();
			g.setColor(Color.YELLOW);
			g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
	}

	// 绘制棋盘
	private void drawBoard(Graphics g) {
		g.setColor(Color.BLACK);

		// 绘制横线
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			int y = (int) ((i + 0.5) * CELL_SIZE);
			g.drawLine((int) CELL_SIZE / 2, y, (int) ((BOARD_WIDTH - 0.5) * CELL_SIZE), y);
		}

		// 绘制竖线
		for (int i = 0; i < BOARD_WIDTH; i++) {
			int x = (int) ((i + 0.5) * CELL_SIZE);
			// 上半部分
			if (i == 0 || i == BOARD_WIDTH - 1) {
				g.drawLine(x, (int) CELL_SIZE / 2, x, (int) ((BOARD_HEIGHT - 0.5) * CELL_SIZE));
			} else {
				g.drawLine(x, (int) CELL_SIZE / 2, x, (int) ((BOARD_HEIGHT / 2 - 0.5) * CELL_SIZE));
				g.drawLine(x, (int) ((BOARD_HEIGHT / 2 + 0.5) * CELL_SIZE), x,
						(int) ((BOARD_HEIGHT - 0.5) * CELL_SIZE));
			}
		}

		// 绘制"楚河汉界"
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString("楚 河        汉 界", (int) (2.2 * CELL_SIZE), (int) (5.5 * CELL_SIZE - 20));

		// 绘制九宫格斜线
		g.drawLine((int) (3.5 * CELL_SIZE), (int) (0.5 * CELL_SIZE), (int) (5.5 * CELL_SIZE), (int) (2.5 * CELL_SIZE));
		g.drawLine((int) (5.5 * CELL_SIZE), (int) (0.5 * CELL_SIZE), (int) (3.5 * CELL_SIZE), (int) (2.5 * CELL_SIZE));
		g.drawLine((int) (3.5 * CELL_SIZE), (int) (7.5 * CELL_SIZE), (int) (5.5 * CELL_SIZE), (int) (9.5 * CELL_SIZE));
		g.drawLine((int) (5.5 * CELL_SIZE), (int) (7.5 * CELL_SIZE), (int) (3.5 * CELL_SIZE), (int) (9.5 * CELL_SIZE));
	}

	// 绘制棋子
	private void drawPieces(Graphics g) {
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				if (board.getBoard()[row][col] != null) {
					// 绘制单个棋子
					int x = col * CELL_SIZE;
					int y = row * CELL_SIZE;

					// 绘制棋子背景
					if (board.getBoard()[row][col].isRed()) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.BLACK);
					}
					g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);

					// 绘制棋子边框
					g.setColor(Color.WHITE);
					g.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);

					// 绘制棋子文字
					g.setColor(board.getBoard()[row][col].isRed() ? Color.WHITE : Color.WHITE);
					g.setFont(new Font("宋体", Font.BOLD, 20));
					String text = board.getDisplayName(row, col);
					FontMetrics fm = g.getFontMetrics();
					int textWidth = fm.stringWidth(text);
					int textHeight = fm.getHeight();
					g.drawString(text, x + (CELL_SIZE - textWidth) / 2, y + (CELL_SIZE + textHeight / 2) / 2);
				}
			}
		}
	}

	public void registerListener(PageSwitchListener listener) {
		this.listener = listener;
	}

}



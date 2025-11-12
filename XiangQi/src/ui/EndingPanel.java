package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Board;
import model.ChessBoard;

public class EndingPanel extends JPanel implements PageSwitchSubject{
	private Board board;
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private int CELL_SIZE;
	
	private PageSwitchListener listener;

	public EndingPanel() {
		board = new ChessBoard();
		BOARD_WIDTH = board.getBoardWidth();
		BOARD_HEIGHT = board.getBoardHeight();
		CELL_SIZE = board.getCellSize();

		setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
		setBackground(Color.BLACK);

		initializeBoard();
	}

	private void initializeBoard() {
		// 创建标题
		JLabel titleLabel = new JLabel("游戏结束", JLabel.CENTER);
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		// 创建按钮面板
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false); // 透明背景
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 20));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 150, 200));
		
		// 创造再来一局按钮
		JButton againButton = createStyledButton("再来一局");
		againButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 切换到游戏界面
				listener.switchToPage("Game");
			}
		});
		
		// 创建退出按钮
		JButton exitButton = createStyledButton("退出游戏");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(EndingPanel.this, "确定要退出游戏吗？", "退出确认",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		// 添加按钮到按钮面板
		buttonPanel.add(againButton);
		buttonPanel.add(exitButton);

		// 添加组件到主面板
		add(titleLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("微软雅黑", Font.BOLD, 24));
		button.setBackground(new Color(70, 130, 180));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setPreferredSize(new Dimension(200, 60));

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

	public void registerListener(PageSwitchListener listener) {
		this.listener = listener;
	}

}

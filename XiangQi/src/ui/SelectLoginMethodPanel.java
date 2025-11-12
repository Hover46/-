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
import javax.swing.JPasswordField;

import model.Board;
import model.ChessBoard;
import model.UserData;

public class SelectLoginMethodPanel extends JPanel implements PageSwitchSubject {
	private Board board;
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private int CELL_SIZE;

	private PageSwitchListener listener;

	public SelectLoginMethodPanel() {
		board = new ChessBoard();
		BOARD_WIDTH = board.getBoardWidth();
		BOARD_HEIGHT = board.getBoardHeight();
		CELL_SIZE = board.getCellSize();

		setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
		setBackground(Color.BLACK);

		initializeBoard();
	}

	private void initializeBoard() {
		// 创建按钮面板
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false); // 透明背景
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 20));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 150, 200));

		// 创建游客按钮
		JButton startButton = createStyledButton("游客");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 切换到游戏界面
				listener.switchToPage("TouristGameMenu");
			}
		});

		// 创建登录按钮
		JButton settingsButton = createStyledButton("登录");
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

				JLabel nameLabel = new JLabel("请输入账号");
				JPasswordField nameField = new JPasswordField();

				JLabel passwordLabel = new JLabel("请输入密码");
				JPasswordField passwordField = new JPasswordField();

				// 切换可不可见？双重密码认证？这里有很多异常需要处理
				panel.add(nameLabel);
				panel.add(nameField);
				panel.add(passwordLabel);
				panel.add(passwordField);

				int result = JOptionPane.showConfirmDialog(SelectLoginMethodPanel.this, panel, "请输入账号和密码:",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					char[] name = passwordField.getPassword();
					char[] password = passwordField.getPassword();

					if(PasswordChecker.isPasswordRight(new String(name), new String(password))) {
						// 设置玩家
						UserData.getInstance().setUserInGame(new String(name));
						// 切换到游戏界面
						listener.switchToPage("Game");
					}else {
						System.out.println("账号或密码错误");
					}
					
					// 清空数组
					java.util.Arrays.fill(name, ' ');
					java.util.Arrays.fill(password, ' ');
				}
			}
		});

		// 创建注册按钮
		JButton exitButton = createStyledButton("注册");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

				JLabel nameLabel = new JLabel("请输入账号");
				JPasswordField nameField = new JPasswordField();

				JLabel passwordLabel = new JLabel("请输入密码");
				JPasswordField passwordField = new JPasswordField();

				panel.add(nameLabel);
				panel.add(nameField);
				panel.add(passwordLabel);
				panel.add(passwordField);

				int result = JOptionPane.showConfirmDialog(SelectLoginMethodPanel.this, panel, "请输入账号和密码:",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					char[] name = passwordField.getPassword();
					char[] password = passwordField.getPassword();
					
					// 传入数据库
					UserData userData = UserData.getInstance();
					userData.setUserData(new String(name), new String(password));
					// 存档
					SaveManager.saveData(userData, "UserData");

					// 清空数组
					java.util.Arrays.fill(name, ' ');
					java.util.Arrays.fill(password, ' ');
					
				}
			}
		});
		// 添加按钮到按钮面板
		buttonPanel.add(startButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(exitButton);

		// 添加组件到主面板
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

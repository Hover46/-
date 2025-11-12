package ui;
import javax.swing.*;

import java.awt.*;
import java.util.HashMap;

public class Player extends JFrame implements PageSwitchListener {
//	private CardLayout cardLayout;
	private JPanel mainPanel;

	private JLabel statusLabel;

	private HashMap<String, PageSwitchSubject> hashMap = new HashMap<String, PageSwitchSubject>();

	public Player() {

		// 测试是否能正常上传
		setTitle("中国象棋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// 创建卡片布局，用于切换不同界面
//		cardLayout = new CardLayout();
		mainPanel = new JPanel();

		// 创造开始界面
		EnterPanel enterPanel = new EnterPanel();

		// 创造登录界面
		SelectLoginMethodPanel selectLoginMethodPanel = new SelectLoginMethodPanel();

		// 创建游戏界面
		TouristGamePanel touristGamePanel = new TouristGamePanel();
		GamePanel gamePanel = new GamePanel();

		// 创造结束界面
		EndingPanel endingPanel = new EndingPanel();

		// 将字符串与对象对应
		// 有时间可以把key改成类方便报错（就是感觉为了传个值而创造一个实例很怪。直接传值肯定不行，因为要用的是这个类中的值，把值转成String传过来？
		hashMap.put("StartMenu", enterPanel);
		hashMap.put("SelectLoginMethodMenu", selectLoginMethodPanel);
		hashMap.put("TouristGameMenu", touristGamePanel);
		hashMap.put("Game", gamePanel);
		hashMap.put("EndMenu", endingPanel);

		// 自动调为同一大小/大小虽页面调整
		mainPanel.add((Component) hashMap.get("StartMenu"), "StartMenu");
//		mainPanel.add((Component)hashMap.get("SelectLoginMethodMenu"), "SelectLoginMethodMenu");
//		mainPanel.add((Component) hashMap.get("TouristGameMenu"), "TouristGameMenu");
//		mainPanel.add((Component)hashMap.get("Game"), "Game");
//		mainPanel.add((Component)hashMap.get("EndMenu"), "EndMenu");

		// 创建状态栏（给底下一个边框好看点）
		statusLabel = new JLabel("    ");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// 布局
		setLayout(new BorderLayout());
		add(statusLabel, BorderLayout.SOUTH);
		add(mainPanel, BorderLayout.CENTER);

		// 居中显示
		pack();
		setLocationRelativeTo(null);

		// 让初始界面可以提供换页命令
		enterPanel.registerListener(this);

	}

	public void switchToPage(String pagename) {
		// 切换页面
		// *
		mainPanel.removeAll();
		mainPanel.add((Component) hashMap.get(pagename), pagename);
		mainPanel.revalidate();
		mainPanel.repaint();
		// *
		
//		cardLayout.show(mainPanel, pagename);
		
		// *
		pack();
		// *
		
		// 将提供换页命令的主题更改为这个页面
		hashMap.get(pagename).registerListener(this);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Player().setVisible(true);
		});
	}
}

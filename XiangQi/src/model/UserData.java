package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Data, Serializable{
	private static UserData uniqueInstance;
	private ArrayList<String[]> userdatas = new ArrayList<String[]>();
	
	private String userInGame = new String();
	
	private UserData() {};
	
	public static UserData getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new UserData();
		}
		return uniqueInstance;
	}
	
	// 只在读档时统一改变地址
	public static void setUniqueInstance(UserData uniqueInstance) {
		UserData.uniqueInstance = uniqueInstance;
	}

	public String getUserInGame() {
		return userInGame;
	}
	
	public  ArrayList<String[]> getUserDatas() {
		return userdatas;
	}

	public void setUserInGame(String userInGame) {
		this.userInGame = userInGame;
	}

	public void setUserData(String name, String password) {
		String[] userdata = new String[2];
		userdata[0] = name;
		userdata[1] = password;
		userdatas.add(userdata);
	}

}

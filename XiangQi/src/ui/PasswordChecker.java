package ui;
import java.util.ArrayList;

import model.UserData;

public class PasswordChecker {
	public static Boolean isPasswordRight(String name, String password) {
		Boolean isPasswordRight = false;
		
		UserData userData = UserData.getInstance();
		ArrayList<String[]> userdatas = userData.getUserDatas();
		for(int i = 0; i < userdatas.size(); i++) {
			if(userdatas.get(i)[0].equals(name) && userdatas.get(i)[1].equals(password)) {
				isPasswordRight = true;
			}
		}
		return isPasswordRight;
	}
}

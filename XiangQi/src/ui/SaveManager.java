package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Data;

public class SaveManager {
	private static final String SAVE_DIR = "saves";

	// 确保保存目录存在
	static {
		File dir = new File(SAVE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static boolean saveData(Data save, String filename) {
		String filepath = SAVE_DIR + File.separator + filename + ".save";

		try (FileOutputStream fileOut = new FileOutputStream(filepath);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

			out.writeObject(save);
			System.out.println("存档成功: " + filepath);
			return true;

		} catch (IOException e) {
			System.err.println("存档失败: " + e.getMessage());
			return false;
		}
	}

	public static Data loadData(String filename) {
		String filepath = SAVE_DIR + File.separator + filename + ".save";

		try (FileInputStream fileIn = new FileInputStream(filepath);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {

			Data save = (Data) in.readObject();
			System.out.println("读档成功: " + filepath);
			return save;

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("读档失败: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取所有存档文件列表
	 * 
	 * @return 存档文件名列表
	 */
	public static List<String> getSaveFiles() {
		List<String> saveFiles = new ArrayList<>();
		File dir = new File(SAVE_DIR);

		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles((d, name) -> name.endsWith(".save"));
			if (files != null) {
				for (File file : files) {
					saveFiles.add(file.getName().replace(".save", ""));
				}
			}
		}
		return saveFiles;
	}

	/**
	 * 删除存档
	 * 
	 * @param filename 文件名
	 * @return 是否删除成功
	 */
	public static boolean deleteSave(String filename) {
		String filepath = SAVE_DIR + File.separator + filename + ".save";
		File file = new File(filepath);

		if (file.exists()) {
			boolean deleted = file.delete();
			if (deleted) {
				System.out.println("删除存档成功: " + filename);
			}
			return deleted;
		}
		return false;
	}
}

import java.io.File;
import java.io.FileOutputStream;

public class MakeLayoutXml {

	private static int baseW = 320;
	private static int baseH = 480;

	private static int[][] list = {{320,480},{ 480, 800 }, { 480, 854 }, { 540, 960 }, { 600, 1024 },
			{ 768, 1024 }, { 720, 1184 }, { 720, 1196 }, { 720, 1280 }, { 768, 1280 }, { 800, 1280 }, { 1080, 1920 },{ 1080, 1776 },
			{ 1440, 2560 },{1536,2048},{1200,1920},{1600,2560} };

	public static void main(String[] args) throws Exception {
		String path = new MakeLayoutXml().getCurrentPath();
		File file = new File(path, "layoutfiles");
		if (!file.exists()) {
			file.mkdirs();
		}
		for (int[] item : list) {
			String itemPath = "values-" + item[1] + "x" + item[0];
			File itemDir = new File(file, itemPath);
			if (!itemDir.exists()) {
				itemDir.mkdirs();
			}
			File dimenWFile = new File(itemDir, "dimen_w.xml");
			FileOutputStream fos = new FileOutputStream(dimenWFile);
			StringBuffer wsb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			wsb.append("<resources>\n");
			for (int i = 1; i <= baseW; i++) {
				int px = (int) (item[0] / (float) baseW * i);
				wsb.append("<dimen name=\"W" + i + "\">" + px + "px</dimen>\n");
			}
			wsb.append("</resources>");
			fos.write(wsb.toString().getBytes());
			fos.close();

			File dimenHFile = new File(itemDir, "dimen_h.xml");
			FileOutputStream hfos = new FileOutputStream(dimenHFile);
			StringBuffer hsb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			hsb.append("<resources>\n");
			float everyH = item[1]/(float)baseH;
			for (int i = 1; i <= baseH; i++) {
				int px = (int) (everyH * i);
				hsb.append("<dimen name=\"H" + i + "\">" + px + "px</dimen>\n");
			}
			hsb.append("</resources>");
			hfos.write(hsb.toString().getBytes());
			hfos.close();
		}
	}

	public String getCurrentPath() {
		// 取得根目录路径
		String rootPath = getClass().getResource("/").getFile().toString();
		return rootPath;
	}

}

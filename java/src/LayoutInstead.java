import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LayoutInstead {
	private static final int W = 1080;
	private static final int H = 1920;
	
	private static final String[] rootPaths = { "F:\\xxx\\src\\main\\res\\layout",
			"F:\\xxx\\src\\main\\res\\drawable" };
	
	private static final String[] macths = { "width=\"0px\"", "Left=\"0px\"", "Right=\"0px\"", "Size=\"0px\"",
			"margin=\"0px\"", "radius=\"0px\"", "horizontalSpacing=\"0px\"" };
	private static final String[] places = { "width=\"@dimen/W0\"", "Left=\"@dimen/W0\"", "Right=\"@dimen/W0\"",
			"Size=\"@dimen/W0\"", "margin=\"@dimen/W0\"", "radius=\"@dimen/W0\"", "horizontalSpacing=\"@dimen/W0\"" };

	private static final String[] macths1 = { "height=\"0px", "Top=\"0px", "Bottom=\"0px", "lineSpacingExtra=\"0px",
			"verticalSpacing=\"0px", "dividerHeight=\"0px" };
	private static final String[] places1 = { "height=\"@dimen/H0", "Top=\"@dimen/H0", "Bottom=\"@dimen/H0",
			"lineSpacingExtra=\"@dimen/H0", "verticalSpacing=\"@dimen/H0", "dividerHeight=\"@dimen/H0" };

	public static void main(String[] args) throws Exception {

		float eachW = W / 320.f;
		float eachH = H / 480.f;
		for (int y = 0; y < rootPaths.length; y++) {
			File root = new File(rootPaths[y]);
			File[] list = root.listFiles();
			for (int i = 0; i < list.length; i++) {
				if (!list[i].getName().endsWith(".xml"))
					return;
				String text = "";
				InputStreamReader read = new InputStreamReader(new FileInputStream(list[i]), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					text += line + "\n";
				}
				read.close();
				System.out.println(text);
				for (int j = 1; j <= W; j++) {
					for (int x = 0; x < macths.length; x++) {
						String search = macths[x].replace("0", String.valueOf(j));
						System.out.println("search width:" + search);
						String replace = "";
						for (int k = 0; k < 320; k++) {
							int left = Math.round(k * eachW);
							int right = Math.round((k + 1) * eachW) + 1;
							if (j >= left && j < right) {

								replace = places[x].replace("0", String.valueOf(k == 0 ? 1 : k));
								text = text.replaceAll(search, replace);
							}
						}
					}
				}

				for (int j = 1; j <= H; j++) {
					for (int x = 0; x < macths1.length; x++) {
						String search = macths1[x].replace("0", String.valueOf(j));
						System.out.println("search height:" + search);
						String replace = "";
						for (int k = 0; k < 480; k++) {
							int left = Math.round(k * eachH);
							int right = Math.round((k + 1) * eachH) + 1;
							if (j >= left && j < right) {
								replace = places1[x].replace("0", String.valueOf(k == 0 ? 1 : k));
								text = text.replaceAll(search, replace);
							}
						}
					}
				}

				System.out.println(text);
				FileOutputStream hfos = new FileOutputStream(list[i]);
				OutputStreamWriter write = new OutputStreamWriter(hfos, "UTF-8");
				BufferedWriter writer = new BufferedWriter(write);
				writer.write(text);
				writer.close();
			}
		}
	}
}

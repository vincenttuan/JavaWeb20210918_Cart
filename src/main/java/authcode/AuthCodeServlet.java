package authcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/code")
public class AuthCodeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得驗證碼
		String authCode = String.format("%04d", new Random().nextInt(10000)); // 0~9999
		System.out.println("authCode: " + authCode);
		try {
			// 取得圖片
			BufferedImage img = getAuthCodeImage(authCode);
			// 發送圖片
			ImageIO.write(img, "JPEG", resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	// 產生圖檔
	private BufferedImage getAuthCodeImage(String authCode) {
		int w = 80;
		int h = 30;
		// 建立圖像暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
		// 建立畫布
		Graphics g = img.getGraphics();
		// 設定顏色
		g.setColor(Color.YELLOW);
		// 塗滿背景
		g.fillRect(0, 0, w, h);
		
		// 繪文字
		//g.setFont(new Font("新細明體", Font.BOLD, 30)); // 設定自型
		//g.drawString(authCode, 10, 25); // 在座標(10, 25) 上畫 authCode 的內容
		g.setColor(Color.BLACK); // 設定顏色
		g.setFont(new Font("新細明體", Font.BOLD, 30)); // 設定自型
		g.drawString(authCode.charAt(0)+"", 10, 25); // 在座標(10, 25) 上畫 authCode[0] 的內容
		
		g.setColor(Color.RED);
		g.setFont(new Font("新細明體", Font.BOLD, 20)); // 設定自型
		g.drawString(authCode.charAt(1)+"", 25, 25); // 在座標(25, 25) 上畫 authCode[1] 的內容
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("新細明體", Font.BOLD, 10)); // 設定自型
		g.drawString(authCode.charAt(2)+"", 40, 15); // 在座標(40, 15) 上畫 authCode[2] 的內容
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("新細明體", Font.BOLD, 15)); // 設定自型
		g.drawString(authCode.charAt(3)+"", 60, 25); // 在座標(60, 25) 上畫 authCode[3] 的內容
		
		// 加入干擾線
		g.setColor(Color.GRAY);
		Random r = new Random();
		for(int i=1;i<=10;i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(w);
			g.drawLine(x1, y1, x2, y2);
		}
		
		return img;
	}
	
	
	
	
}

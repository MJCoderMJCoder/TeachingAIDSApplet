

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 */

/**
 * @author MJCoder
 *
 */
public class AudioDemo extends Applet {
	private AudioClip clip;
	private AppletContext context;
	private Player player;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5709697300034575308L;

	/**
	 * @throws HeadlessException
	 */
	public AudioDemo() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 该方法的目的是为 Applet提供所需的任何初始化。在HTML的Applet标记内的param标签被处理后调用该方法。
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		context = this.getAppletContext();
		String audioURL = this.getParameter("audio");
		if (audioURL == null) {
			audioURL = "audio/1.mp3";
		}
		try {
			URL url = new URL(this.getDocumentBase(), audioURL);
			// clip = context.getAudioClip(url);
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(url.getFile()));
			player = new Player(buffer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 浏览器调用init()方法后，该方法被自动调用。每当用户从其他页面返回到包含Applet的页面时，则调用该方法。
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	/**
	 * 该方法在start()方法之后立即被调用，或者在Applet需要重绘在浏览器的时候调用。paint()方法实际上继承于 java.awt。
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawString("音频测试", 50, 50);
		try {
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 当用户从包含Applet的页面移除的时候，该方法自动被调用。因此，可以在相同的Applet中反复调用该方法。
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		player.close();
	}

	/**
	 * 此方法仅当浏览器正常关闭时调用。因为Applet只有在HTML网页上有效，所以不应该在用户离开包含Applet的页面后遗漏任何资源。
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}

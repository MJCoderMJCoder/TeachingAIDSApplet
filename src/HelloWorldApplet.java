

/**
 * 
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.HeadlessException;

/**
 * @author MJCoder
 *
 */
public class HelloWorldApplet extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2090639840834903266L;

	/**
	 * @throws HeadlessException
	 */
	public HelloWorldApplet() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 该方法的目的是为 Applet提供所需的任何初始化。在HTML的Applet标记内的param标签被处理后调用该方法。
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
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
		g.drawString("Hello Applet Test", 25, 50);
	}

	/**
	 * 当用户从包含Applet的页面移除的时候，该方法自动被调用。因此，可以在相同的Applet中反复调用该方法。
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
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

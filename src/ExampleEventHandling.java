

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 */

/**
 * @author MJCoder
 *
 */
public class ExampleEventHandling extends Applet implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1775615334021335148L;
	private String str;
	private int i = 0;

	/**
	 * @throws HeadlessException
	 */
	public ExampleEventHandling() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 重绘界面
	 * 
	 * @param string
	 *            字符串修改
	 */
	private void addItem(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		str = string + "\n";
		repaint();
	}

	/**
	 * 该方法的目的是为 Applet提供所需的任何初始化。在HTML的Applet标记内的param标签被处理后调用该方法。
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		addMouseListener(this);
		addItem("initializing the applet ");
	}

	/**
	 * 浏览器调用init()方法后，该方法被自动调用。每当用户从其他页面返回到包含Applet的页面时，则调用该方法。
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		addItem("starting the applet ");
	}

	/**
	 * 该方法在start()方法之后立即被调用，或者在Applet需要重绘在浏览器的时候调用。paint()方法实际上继承于 java.awt。
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		// Draw a Rectangle around the applet's display area.
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		// display the string inside the rectangle.
		g.drawString(str.toString(), 10, 20);
	}

	/**
	 * 当用户从包含Applet的页面移除的时候，该方法自动被调用。因此，可以在相同的Applet中反复调用该方法。
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		addItem("stopping the applet ");
	}

	/**
	 * 此方法仅当浏览器正常关闭时调用。因为Applet只有在HTML网页上有效，所以不应该在用户离开包含Applet的页面后遗漏任何资源。
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		addItem("unloading the applet");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		addItem("mouse clicked! ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		addItem("mouse pressed! ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		addItem("mouse released! ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		addItem("mouse entered! ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		addItem("mouse exited! ");
	}

}

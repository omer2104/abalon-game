import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * the panel that is incharge of the background of the main frame
 * 
 * @author omer
 *
 */
public class AbalonBoardGamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Img _image;

	/**
	 * constructor of panel
	 * 
	 * @param image
	 */
	public AbalonBoardGamePanel(Img image) {
		_image = image;
	}

	/**
	 * paint the background
	 */
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_image.drawImg(g);
	}

	/**
	 * draw background
	 */
	public void drawBackground() {
		repaint();
	}

	public Img getImage() {
		return _image;
	}

	public void setImage(Img _image) {
		this._image = _image;
	}
}

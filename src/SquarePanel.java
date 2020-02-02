
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

import enums.Player;

/**
 * squre panel that is in charge of drawing a single square
 * @author omer
 *
 */
public class SquarePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private Img _image;
	private Point _pos;
	private LinkedList<SquareListener> _listeners;
	private Player _player;
	private int _size;
	
	/**
	 * constructor
	 * @param pos
	 * @param size
	 */
	public SquarePanel(Point pos,int size)
	{
		setOpaque(false);
		_listeners=new LinkedList<SquareListener>();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				super.mousePressed(arg0);
				for (SquareListener squareListener : _listeners) {
					squareListener.squarePressed(_pos);
				}
			}
		});
		_player=null;
		_image=null;
		_size=size;
		_pos=pos;
	}
	/**
	 * constructor
	 * @param pos
	 * @param size
	 * @param p
	 */
	public SquarePanel(Point pos,int size,Player p)
	{
		setOpaque(false);
		_listeners=new LinkedList<SquareListener>();
		_player=p;
		_image=null;
		_size=size;
		_pos=pos;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//System.out.println(_pos.toString());
				for (SquareListener squareListener : _listeners) {
					squareListener.squarePressed(_pos);
				}
			}
		});
	}

	/**
	 * add listener to the square
	 * @param l
	 */
	public void addListener(SquareListener l) {_listeners.add(l);}
	
	/**
	 * in charge of drawing the image
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(_image!=null)
		{
			_image.drawImg(g);
		}
	}
	
	/**
	 * draw square
	 */
	public void drawSquare()
	{
		if(_player==null)
		{
			_image=null;
		}
		if(_player==Player.WHITE)
		{
			_image=new Img("Images\\white-ball.png", 0, 0, _size, _size);
		}
		if(_player==Player.BLACK)
		{
			_image=new Img("Images\\black-ball.png", 0, 0, _size, _size);
		}
		repaint();
	}
	
	/**
	 * a function that needs to light a soldier
	 */
	public void lightSoldier()
	{
		if(_player==Player.WHITE)
		{
			_image=new Img("Images\\white ball light yellow.png", 0, 0, _size, _size);
		}
		if(_player==Player.BLACK)
		{
			_image=new Img("Images\\black ball light yellow.png", 0, 0, _size, _size);
		}
		repaint();
	}
	
	/**
	 * mark a square
	 */
	public void markSquare()
	{
		if(_player==null)
		{
			_image=new Img("Images\\empty marked.png", 0, 0, _size, _size);
		}
		if(_player==Player.WHITE)
		{
			_image=new Img("Images\\white marked.png", 0, 0, _size, _size);
		}
		if(_player==Player.BLACK)
		{
			_image=new Img("Images\\black marked.png", 0, 0, _size, _size);
		}
		repaint();
	}

	/***************************/
	public Point get_pos() {
		return _pos;
	}

	public void set_pos(Point _pos) {
		this._pos = _pos;
	}

	public LinkedList<SquareListener> get_listeners() {
		return _listeners;
	}

	public void set_listeners(LinkedList<SquareListener> _listeners) {
		this._listeners = _listeners;
	}

	public Player getPlayer() {
		return _player;
	}

	public void setPlayer(Player _player) {
		this._player = _player;
	}

	public int get_size() {
		return _size;
	}

	public void set_size(int _size) {
		this._size = _size;
	}

	public Img get_image() {
		return _image;
	}

	public void set_image(Img _image) {
		this._image = _image;
	}
}

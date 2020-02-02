import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import enums.Player;

/**
 * the panel that paints all the squares
 * 
 * @author omer
 *
 */
public class AbalonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int _numOfRows;
	private int _numOfColsInFirstRow;
	private final int _startX = 181;
	private final int _startY = 74;
	private final int _squareSize = 35;
	private Player[][] _board;
	private SquarePanel[][] _panels;
	private AbalonFrame _listener;
	private Img _backgroundImage;

	/**
	 * constructor
	 * 
	 * @param numOfRows
	 * @param numOfColsInFirstRow
	 * @param af
	 */
	public AbalonPanel(int numOfRows, int numOfColsInFirstRow, AbalonFrame af) {
		setLayout(null);
		_numOfRows = numOfRows;
		_numOfColsInFirstRow = numOfColsInFirstRow;
		_panels = new SquarePanel[_numOfRows][_numOfRows];
		_board = null;
		_listener = af;
		_backgroundImage = new Img("Images\\newboard.png", 0, 0, af.getWidth(),
				af.getHeight() - 32);
		repaint();
	}

	/**
	 * initializes panels
	 */
	public void initPanels() {
		int curX = _startX, curY = _startY;
		int colsInRow = _numOfColsInFirstRow;
		int rows = _numOfRows / 2;
		Point pos;
		// all the rows except the middle row
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colsInRow; j++) {
				pos = new Point(j, i);
				_panels[i][j] = new SquarePanel(pos, _squareSize, _board[i][j]);
				_panels[i][j].addListener(_listener);
				_panels[i][j].setBounds(curX, curY, _squareSize, _squareSize);
				add(_panels[i][j]);
				_panels[i][j].drawSquare();
				pos = new Point(j, _numOfRows - 1 - i);
				_panels[_numOfRows - 1 - i][j] = new SquarePanel(pos,
						_squareSize, _board[_numOfRows - 1 - i][j]);
				_panels[_numOfRows - 1 - i][j].addListener(_listener);
				_panels[_numOfRows - 1 - i][j].setBounds(curX - 3, _startY
						+ _squareSize * (_numOfRows - 1 - i) + 7, _squareSize,
						_squareSize);
				add(_panels[_numOfRows - 1 - i][j]);
				_panels[_numOfRows - 1 - i][j].drawSquare();
				curX += _squareSize + 5 + 1 * j - Math.round(0.2 * j);
			}
			curY += _squareSize + 2;
			curX = _startX - (i + 1) * (_squareSize + 4) + (_squareSize / 2)
					* (i + 1);
			colsInRow++;
		}
		// middle row
		for (int i = 0; i < colsInRow; i++) {
			pos = new Point(i, _numOfRows / 2);
			_panels[_numOfRows / 2][i] = new SquarePanel(pos, _squareSize,
					_board[_numOfRows / 2][i]);
			_panels[_numOfRows / 2][i].addListener(_listener);
			_panels[_numOfRows / 2][i].setBounds(curX, curY, _squareSize,
					_squareSize);
			add(_panels[_numOfRows / 2][i]);
			_panels[_numOfRows / 2][i].drawSquare();
			curX += _squareSize + 5 + 1 * i - Math.round(0.3 * i);
		}
	}

	/**
	 * repaint the board squares
	 */
	public void setPanelsByBoard() {
		int colsInRow = _numOfColsInFirstRow;
		int rows = _numOfRows / 2;
		// all the rows except the middle row
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colsInRow; j++) {
				_panels[i][j].setPlayer(_board[i][j]);
				_panels[i][j].drawSquare();
				_panels[_numOfRows - 1 - i][j].setPlayer(_board[_numOfRows - 1
						- i][j]);
				_panels[_numOfRows - 1 - i][j].drawSquare();
			}
			colsInRow++;
		}
		// middle row
		for (int i = 0; i < colsInRow; i++) {
			_panels[_numOfRows / 2][i].setPlayer(_board[_numOfRows / 2][i]);
			_panels[_numOfRows / 2][i].drawSquare();
		}
	}

	/**
	 * paints the background
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		_backgroundImage.drawImg(g);
	}

	// getters and setters
	public int get_numOfRows() {
		return _numOfRows;
	}

	public void set_numOfRows(int _numOfRows) {
		this._numOfRows = _numOfRows;
	}

	public int get_numOfColsInFirstRow() {
		return _numOfColsInFirstRow;
	}

	public void set_numOfColsInFirstRow(int _numOfColsInFirstRow) {
		this._numOfColsInFirstRow = _numOfColsInFirstRow;
	}

	public Player[][] get_board() {
		return _board;
	}

	public void set_board(Player[][] _board) {
		this._board = _board;
	}

	public SquarePanel[][] get_panels() {
		return _panels;
	}

	public void set_panels(SquarePanel[][] _panels) {
		this._panels = _panels;
	}

	public int get_startX() {
		return _startX;
	}

	public int get_startY() {
		return _startY;
	}

	public int get_squareSize() {
		return _squareSize;
	}

}

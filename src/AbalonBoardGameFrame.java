import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import abalonBoardGame.AbalonBoardGame;

/**
 * the main game board frame
 * 
 * @author omer
 *
 */
public class AbalonBoardGameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _width;
	private int _height;
	private Img _backgroundImage;
	private AbalonBoardGamePanel _background;
	private JButton _exitButton, _startButton, _instructionButton,
			_exitInstructionsButton;

	/**
	 * constructor that builds the frame
	 */
	public AbalonBoardGameFrame() {
		int bW = 200;
		int bH = 50;
		setLayout(null);
		_width = 750;
		_height = 500;
		_exitButton = new JButton("Exit");
		_exitButton.setBounds(_width - bW - 30, _height - bH - 50, bW, bH);
		_exitButton.setBackground(Color.orange);
		_exitButton.setFont(new Font("Ravie", 1, 24));
		_startButton = new JButton("Start");
		_startButton.setBounds(_width - bW - 30, _height - bH * 3 - 50 - 20,
				bW, bH);
		_startButton.setBackground(Color.orange);
		_startButton.setFont(new Font("Ravie", 1, 24));
		_instructionButton = new JButton("Instructions");
		_instructionButton.setBounds(_width - bW - 30, _height - bH * 2 - 50
				- 10, bW, bH);
		_instructionButton.setBackground(Color.orange);
		_instructionButton.setFont(new Font("Ravie", 1, 16));
		_exitInstructionsButton = new JButton("Back");
		_exitInstructionsButton.setBounds(_width - bW - 30, _height - bH - 50,
				bW, bH);
		_exitInstructionsButton.setBackground(Color.orange);
		_exitInstructionsButton.setFont(new Font("Ravie", 1, 24));
		_exitInstructionsButton.setVisible(false);
		_exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// close application
				// normal termination
				System.exit(0);
			}
		});
		_startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// start the abalon game
				int numOfRows = 9, numOfColsInFirstRow = 5;
				AbalonBoardGame abalon = new AbalonBoardGame(numOfRows,
						numOfColsInFirstRow);
				abalon.initBoard();
				AbalonFrame af = new AbalonFrame(numOfRows,
						numOfColsInFirstRow, abalon);
				af.setBoard();
			}
		});
		_instructionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int bW = 200;
				int bH = 50;
				// show instructions
				int width = 1300, height = 611;
				// resize the frame
				resizeWindow(width, height);
				_exitInstructionsButton.setBounds(_width - bW - 50, _height
						- bH - 50, bW, bH);
				_exitInstructionsButton.setVisible(true);
				_exitButton.setVisible(false);
				_startButton.setVisible(false);
				_instructionButton.setVisible(false);
				// draw instructions
				_backgroundImage = new Img("Images\\instructions2.png", 0, 0,
						width - 20, height - 20);
				_background.setImage(_backgroundImage);
				_background.drawBackground();
			}
		});
		_exitInstructionsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// get back to main screen
				_exitInstructionsButton.setVisible(false);
				_exitButton.setVisible(true);
				_startButton.setVisible(true);
				_instructionButton.setVisible(true);
				// resize the frame
				resizeWindow(750, 500);
				// redraw background
				_backgroundImage = new Img("Images\\Abalon7.png", 0, 0,
						_width - 10, _height - 20);
				_background.setImage(_backgroundImage);
				_background.drawBackground();
			}
		});
		add(_exitButton);
		add(_startButton);
		add(_instructionButton);
		add(_exitInstructionsButton);
		_backgroundImage = new Img("Images\\Abalon7.png", 0, 0, _width - 10,
				_height - 20);
		_background = new AbalonBoardGamePanel(_backgroundImage);
		_background.setBounds(0, 0, _width, _height);
		add(_background);
		setVisible(true);
		setSize(_width, _height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * resize the dimentions of the frames
	 * 
	 * @param width
	 * @param height
	 */
	private void resizeWindow(int width, int height) {
		_width = width;
		_height = height;
		setSize(_width, _height);
		// match background panel to frame size
		_background.setBounds(0, 0, _width, _height);
	}
}

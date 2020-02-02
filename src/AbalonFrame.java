import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import boardAI.GameBoardAI;
import boardGame.Board;
import abalonBoardGame.AbalonBoard;
import abalonBoardGame.AbalonBoardDataStructure;
import abalonBoardGame.AbalonBoardGame;
import abalonBoardGame.AbalonSoldier;
import enums.Player;
import exceptions.AbalonException;


/**
 * the frame that presents the game
 * @author omer
 *
 */
public class AbalonFrame extends JFrame implements SquareListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private AbalonPanel _ap;
	private AbalonBoardGame _ab;
	private GameBoardAI<Board<AbalonBoardDataStructure, AbalonSoldier[][]>> _ai;
	private Point _source,_dest;
	private Vector<Point> _points;
	private Player _winner;
	private JButton _reset;
	private JButton _exit;
	private int _width,_height;
	private Timer _timer=new Timer(0, new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			((Timer)e.getSource()).stop();
			compMove();
		}
	});

	/**
	 * constructor
	 * @param numOfRows
	 * @param numOfColsInFirstRow
	 * @param ab
	 */
	public AbalonFrame(int numOfRows,int numOfColsInFirstRow,AbalonBoardGame ab)
	{
		setLayout(null);
		int bW=200,bH=50;
		_width=850;_height=500;
		setVisible(true);
		setSize(_width, _height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_ap=new AbalonPanel(numOfRows,numOfColsInFirstRow,this);
		_ap.setBounds(0, 0, _width, _height);
		add(_ap);
		_ab=ab;
		_source=_dest=null;
		_winner=null;
		_ai=new GameBoardAI<Board<AbalonBoardDataStructure, AbalonSoldier[][]>>();
		_ai.setLevel(3);
		_reset=new JButton("Restart");
		_reset.setBounds(_width-bW-30,_height-bH*2-50-10,bW,bH);
		_reset.setBackground(Color.orange);
		_reset.setFont(new Font("Ravie", 1, 24));
		_reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// reset the board
				_ab.initBoard();
				setBoard();
			}
		});
		_exit=new JButton("Exit");
		_exit.setBounds(_width-bW-30,_height-bH-50,bW,bH);
		_exit.setBackground(Color.orange);
		_exit.setFont(new Font("Ravie", 1, 24));
		_exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// close the window
				dispose();
			}
		});
		_exit.setVisible(true);
		add(_exit);
		_reset.setVisible(true);
		add(_reset);

	}

	/**
	 * draw board
	 */
	public void setBoard()
	{
		if(_ap.get_board()==null)
		{
			_ap.set_board(_ab.boardDescription());
			_ap.initPanels();
		}
		else
		{
			_ap.set_board(_ab.boardDescription());
			_ap.setPanelsByBoard();
		}
	}

	/**
	 * square listener
	 */
	public void squarePressed(Point pos) {
		if(_winner==null)
		{
			if(_source==null || (_source!=null && _source==pos))
			{
				// if player wants to undo his selection of the source soldier
				if(_source!=null && _source==pos)
				{
					// turn off soldier 
					_ap.get_panels()[pos.y][pos.x].drawSquare();
					_source=null;
					// turn off marked points
					turnOffMarkedPoints();
				}
				else
				{
					if(_ab.getBoard().getDataStructure().getSquareContent(pos)!=null)
					{
						// save source location
						_source=pos;
						// paint source in yellow
						_ap.get_panels()[pos.y][pos.x].lightSoldier();
						// calculate positions of possible moves
						_points=validPointsToMove(pos);
						// mark the possible moves on the board
						for (Point point : _points) {
							_ap.get_panels()[point.y][point.x].markSquare();
						}
					}
				}
			}
			else
			{
				_dest=pos;
				Player prevTurn=_ab.getTurn();
				_ab.move(_source, _dest);
				// if player's move was legal
				if(_ab.getTurn()!=prevTurn)
				{
					_source=_dest=null;
					setBoard();
					_winner=_ab.getWinner();
					// if the player has won
					if(_winner!=null)
					{
						System.out.println("The winner is:"+_winner.name());
					}
					else
					{
						// computer move 
						_timer.start();
					}
				}
				// if player's move wasn't legal
				else
				{
					// reset destination point
					_dest=null;
				}
			}
		}
		else 
		{
			System.out.println("The winner is:"+_winner.name());
		}

	}

	/**
	 * computer move
	 */
	private void compMove()
	{
		Collection<Board<AbalonBoardDataStructure, AbalonSoldier[][]>> nextStates=
				_ab.getBoard().getNextStates(_ab.getTurn());
		AbalonBoard aiMove=(AbalonBoard) _ai.findBestMove(nextStates, _ab.getTurn());
		_ab.getBoard().setBoardContent(aiMove);
		setBoard();
		_winner=_ab.getWinner();
		// if the computer has won
		if(_winner!=null)
		{
			System.out.println("The winner is:"+_winner.name());
		}
		// if the computer made his move and didn't win
		else
		{
			// manually switch the turn
			_ab.switchTurn();
		}
	}

	/**
	 * is used to mark the points that the player can move to
	 * @param source
	 * @return
	 */
	private Vector<Point> validPointsToMove(Point source)
	{
		Vector<Point> upm=_ab.getBoard().unconfirmedPossibleMoves(source);
		Vector<Point> moves=new Vector<Point>();
		for (Point point : upm) {
			try {
				_ab.getBoard().validateMove(source, point, _ab.getTurn());
				moves.add(point);
			} catch (AbalonException e) {
				// if the move is legal, add the point to the moves vector
				// otherwise do nothing
			}
		}
		return moves;
	}
	
	/**
	 * turn off the marked points
	 */
	private void turnOffMarkedPoints()
	{
		for (Point point : _points) {
			_ap.get_panels()[point.y][point.x].drawSquare();
		}
		_points=null;
	}

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SudokuGraphics extends JFrame implements ActionListener,
		KeyListener {

	JPanel panel1;
	JPanel panel2;
	JPanel board;
	JButton botReset1;
	JButton botSolve;
	JTextField[][] cells;
	int[][] sudoku;

	SudokuGraphics() {

		sudoku = new int[9][9];
		panel1 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new FlowLayout());
		board = new JPanel(new GridLayout(9, 9, 5, 5));
		botReset1 = new JButton("Init");
		botReset1.setActionCommand("Init");
		botSolve = new JButton("Solve");
		botSolve.setActionCommand("Solve");

		botSolve.addActionListener(this);
		botReset1.addActionListener(this);

		cells = new JTextField[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = -1;
				cells[i][j] = new JTextField(3);
				cells[i][j].setBackground(new Color(240, 240, 208));
				cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
				cells[i][j].setEnabled(false);
				cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				cells[i][j].addKeyListener(this);
				if ((i < 3 && j > 2 && j < 6) || (i > 2 && i < 6 && j < 3)
						|| (i > 2 && i < 6 && j > 5)
						|| (i > 5 && j > 2 && j < 6)) {
					cells[i][j].setBackground(Color.WHITE);

				}

				board.add(cells[i][j]);

			}
		}

		panel2.add(botReset1);
		panel2.add(botSolve);
		panel1.add(board, BorderLayout.CENTER);
		panel1.add(panel2, BorderLayout.SOUTH);

		this.setTitle("Sudoku");
		this.setBounds(100, 100, 600, 600);
		this.setContentPane(panel1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}

	/*-------------Action Performed-----------------------------*/
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.compareTo("Init") == 0) {
			Initalize();
		} else if (cmd.compareTo("Solve") == 0) {
			panelToMatrix();
			Solve();
			matrixToPanel();
		}
	}

	/*-------------KeyListener methods-----------------------------*/
	public void keyTyped(KeyEvent e) {
		JTextField t = (JTextField) e.getSource();
		if ((t.getText().length() >= 1) || (!Character.isDigit(e.getKeyChar()))
				|| (e.getKeyChar() == '0')) {
			e.setKeyChar('\0');
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	/*-------------------------------------------------------------*/
	public void matrixToPanel() {
		int i = 0, j = 0;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0)// 0 starts empty
				{
					cells[i][j].setText("");
				} else {
					cells[i][j].setText(String.valueOf(sudoku[i][j]));
				}
				cells[i][j].setEnabled(false);
			}
		}
	}

	/*-------------------------------------------------------------*/
	public void panelToMatrix() {
		int i = 0, j = 0;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (cells[i][j].getText().length() <= 0)
					sudoku[i][j] = 0;
				else
					sudoku[i][j] = Integer.parseInt(cells[i][j].getText());
			}
		}

		 sudoku[0][3] = 6;
		 sudoku[0][4] = 2;
		 sudoku[0][5] = 8;
		 sudoku[1][0] = 6;
		 sudoku[1][2] = 2;
		 sudoku[1][8] = 3;
		 sudoku[2][0] = 1;
		 sudoku[2][1] = 8;
		 sudoku[2][2] = 9;
		 sudoku[2][4] = 3;
		 sudoku[2][5] = 4;
		 sudoku[2][6] = 2;
		 sudoku[3][0] = 2;
		 sudoku[3][3] = 4;
		 sudoku[3][8] = 1;
		 sudoku[4][2] = 7;
		 sudoku[4][4] = 5;
		 sudoku[4][6] = 4;
		 sudoku[5][0] = 5;
		 sudoku[5][5] = 9;
		 sudoku[5][8] = 7;
		 sudoku[6][2] = 6;
		 sudoku[6][3] = 2;
		 sudoku[6][4] = 1;
		 sudoku[6][6] = 8;
		 sudoku[6][7] = 5;
		 sudoku[6][8] = 4;
		 sudoku[7][0] = 4;
		 sudoku[7][6] = 9;
		 sudoku[7][8] = 2;
		 sudoku[8][3] = 9;
		 sudoku[8][4] = 4;
		 sudoku[8][5] = 7;
	}

	/*------------------------------------------------------------*/
	public void Initalize() // Enable cells to put values
	{
		int i = 0, j = 0;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				cells[i][j].setText("");
				sudoku[i][j] = 0;
				cells[i][j].setEnabled(true);
			}
		}
	}

	/*---------------------------------------------------------------------*/
	public void Solve()// Solve the game, using the initial values in matrix
	{
		if (checkSudoku())
			if (!solveSudoku(0, 0))
				System.out.println("No solution for this data exists");
	}

	private boolean solveSudoku(int iStart, int jStart) {
		boolean solved = false;
		int nothing = 0;
		if(iStart == 5){
			nothing = 3;
		}
		if (sudoku[iStart][jStart] == 0) {
			int sth = nothing;
			int tryNum = 1;
			while (!solved && tryNum < 10) {
				if (checkEntry(tryNum, iStart, jStart)) {
					sudoku[iStart][jStart] = tryNum;
					solved = callNext(iStart, jStart);
				}
				tryNum++;
			}
			if (!solved) {
				sudoku[iStart][jStart] = 0;
			}

		} else {
			solved = callNext(iStart, jStart);
		}
		return solved;
	}

	private boolean callNext(int iStart, int jStart) {
		int iNext;
		int jNext;
		if (iStart < 8) {
			if (jStart < 8) {
				iNext = iStart;
				jNext = jStart + 1;
			} else {
				iNext = iStart + 1;
				jNext = 0;
			}
		} else {
			if (jStart < 8) {
				iNext = iStart;
				jNext = jStart + 1;
			} else
				return true;
		}
		return solveSudoku(iNext, jNext);
	}

	private boolean checkEntry(int entry, int line, int column) {
		return checkLine(entry, line) && checkColumn(entry, column)
				&& checkSquare(entry, line / 3, column / 3);
	}

	private boolean checkLine(int entry, int line) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[line][i] == entry)
				return false;
		}
		return true;
	}

	private boolean checkColumn(int entry, int column) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][column] == entry)
				return false;
		}
		return true;
	}

	private boolean checkSquare(int entry, int xSquare, int ySquare) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudoku[xSquare * 3 + i][ySquare * 3 + j] == entry)
					return false;
			}
		}
		return true;
	}

	private boolean checkSudoku() {
		return (checkLine() && checkRow() && checkSquare());

	}

	private boolean checkSquare() {
		int xSquare;
		int ySquare;
		int xMod;
		int yMod;
		int entry;
		for (xSquare = 0; xSquare < 3; xSquare++) {
			for (ySquare = 0; ySquare < 3; ySquare++) {
				int checkarray[] = new int[9];
				for (xMod = 0; xMod < 3; xMod++) {
					for (yMod = 0; yMod < 3; yMod++) {
						if ((entry = sudoku[xSquare * 3 + xMod][ySquare * 3
								+ yMod]) != 0)
							checkarray[entry - 1]++;
					}
				}
				if (!checkArray(checkarray))
					return false;
			}
		}
		return true;
	}

	private boolean checkLine() {
		int entry;
		for (int i = 0; i < 9; i++) {
			int checkarray[] = new int[9];
			for (int j = 0; j < 9; j++) {
				if ((entry = sudoku[i][j]) != 0)
					checkarray[entry - 1]++;
			}
			if (!checkArray(checkarray))
				return false;
		}
		return true;
	}

	private boolean checkRow() {
		int entry;
		for (int i = 0; i < 9; i++) {
			int checkarray[] = new int[9];
			for (int j = 0; j < 9; j++) {
				if ((entry = sudoku[j][i]) != 0)
					checkarray[entry - 1]++;
			}
			if (!checkArray(checkarray))
				return false;
		}
		return true;
	}

	private boolean checkArray(int[] a) {
		for (int k = 0; k < 9; k++) {
			// System.out.println(a[k]);
			if (a[k] > 1)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		SudokuGraphics s = new SudokuGraphics();
		s.panelToMatrix();
		// s.Solve();
		s.matrixToPanel();
	}
}
import java.io.BufferedReader;
import java.io.FileReader;

public class CheckToCheck {

	private char[][] board = new char[8][8];

	public static void main(String[] args) {
		CheckToCheck ctc = new CheckToCheck();
		try {
			BufferedReader br = new BufferedReader(new FileReader("boards"));
			String line;
			int lineNr = 0;
			int gameNr = 1;
			boolean fin = true;
			while ((line = br.readLine()) != null) {
				for (int i = 0; i < 8; i++) {
					ctc.board[lineNr][i] = line.charAt(i);
					if(ctc.board[lineNr][i] != '.'){
						fin=false;
					}
				}
				lineNr++;
				if (lineNr == 8) {
					if (ctc.checkForWhite()) {
						System.out.println("Game #" + gameNr
								+ ": White king is in check");
					} else if (ctc.checkForBlack()) {
						System.out.println("Game #" + gameNr
								+ ": Black king is in check");
					} else {
						if(!fin)
						System.out.println("Game #" + gameNr
								+ ": No king is in check");
					}

					lineNr = 0;
					gameNr++;
					fin = true;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}

	private boolean checkForWhite() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j] == 'K') {
					return (checkForDiagWhite(i, j) || checkForLineWhite(i, j)
							|| checkForKnightWhite(i, j) || checkForBlackPawn(
								i, j));
				}
			}
		}
		return false;
	}

	private boolean checkForBlack() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j] == 'k') {
					return (checkForDiagBlack(i, j) || checkForLineBlack(i, j)
							|| checkForKnightBlack(i, j) || checkForWhitePawn(
								i, j));
				}
			}
		}
		return false;
	}

	private boolean checkForDiagWhite(int i, int j) {
		int origI = i;
		int origJ = j;
		while (i > 0 && j > 0) {
			if (board[--i][--j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'b') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i > 0 && j < 7) {
			if (board[--i][++j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'b') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7 && j > 0) {
			if (board[++i][--j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'b') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7 && j < 7) {
			if (board[++i][++j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'b') {
					return true;
				} else
					break;
			}
		}
		return false;
	}

	private boolean checkForDiagBlack(int i, int j) {
		int origI = i;
		int origJ = j;
		while (i > 0 && j > 0) {
			if (board[--i][--j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'B') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i > 0 && j < 7) {
			if (board[--i][++j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'B') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7 && j > 0) {
			if (board[++i][--j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'B') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7 && j < 7) {
			if (board[++i][++j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'B') {
					return true;
				} else
					break;
			}
		}
		return false;
	}

	private boolean checkForLineWhite(int i, int j) {
		int origI = i;
		int origJ = j;
		while (i > 0) {
			if (board[--i][j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'r') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7) {
			if (board[++i][j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'r') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (j > 0) {
			if (board[i][--j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'r') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (j < 7) {
			if (board[i][++j] != '.') {
				if (board[i][j] == 'q'
				// || board[i][j] == 'k'
						|| board[i][j] == 'r') {
					return true;
				} else
					break;
			}
		}
		return false;
	}

	private boolean checkForLineBlack(int i, int j) {
		int origI = i;
		int origJ = j;
		while (i > 0) {
			if (board[--i][j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'R') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (i < 7) {
			if (board[++i][j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'R') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (j > 0) {
			if (board[i][--j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'R') {
					return true;
				} else
					break;
			}
		}
		i = origI;
		j = origJ;
		while (j < 7) {
			if (board[i][++j] != '.') {
				if (board[i][j] == 'Q'
				// || board[i][j] == 'K'
						|| board[i][j] == 'R') {
					return true;
				} else
					break;
			}
		}
		return false;
	}

	private boolean checkForKnightWhite(int i, int j) {
		if (i > 1) {
			if (j < 7) {
				if (board[i - 2][j + 1] == 'n')
					return true;
			}
			if (j > 0) {
				if (board[i - 2][j - 1] == 'n')
					return true;
			}
		}
		if (i < 6) {
			if (j < 7) {
				if (board[i + 2][j + 1] == 'n')
					return true;
			}
			if (j > 0) {
				if (board[i + 2][j - 1] == 'n')
					return true;
			}
		}
		if (j > 1) {
			if (i < 7) {
				if (board[i + 1][j - 2] == 'n')
					return true;
			}
			if (i > 0) {
				if (board[i - 1][j - 2] == 'n')
					return true;
			}
		}
		if (j < 6) {
			if (i < 7) {
				if (board[i + 1][j + 2] == 'n')
					return true;
			}
			if (i > 0) {
				if (board[i - 1][j + 2] == 'n')
					return true;
			}
		}
		return false;
	}

	private boolean checkForKnightBlack(int i, int j) {
		if (i > 1) {
			if (j < 7) {
				if (board[i - 2][j + 1] == 'N')
					return true;
			}
			if (j > 0) {
				if (board[i - 2][j - 1] == 'N')
					return true;
			}
		}
		if (i < 6) {
			if (j < 7) {
				if (board[i + 2][j + 1] == 'N')
					return true;
			}
			if (j > 0) {
				if (board[i + 2][j - 1] == 'N')
					return true;
			}
		}
		if (j > 1) {
			if (i < 7) {
				if (board[i + 1][j - 2] == 'N')
					return true;
			}
			if (i > 0) {
				if (board[i - 1][j - 2] == 'N')
					return true;
			}
		}
		if (j < 6) {
			if (i < 7) {
				if (board[i + 1][j + 2] == 'N')
					return true;
			}
			if (i > 0) {
				if (board[i - 1][j + 2] == 'N')
					return true;
			}
		}
		return false;
	}

	private boolean checkForWhitePawn(int i, int j) {
		if (i == 7) {
			return false;
		} else {
			if (j < 7) {
				if (board[i + 1][j + 1] == 'P') {
					return true;
				}
			}
			if (j > 0) {
				if (board[i + 1][j - 1] == 'P') {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkForBlackPawn(int i, int j) {
		if (i == 0) {
			return false;
		} else {
			if (j < 7) {
				if (board[i - 1][j + 1] == 'p') {
					return true;
				}
			}
			if (j > 0) {
				if (board[i - 1][j - 1] == 'p') {
					return true;
				}
			}
		}
		return false;
	}
}

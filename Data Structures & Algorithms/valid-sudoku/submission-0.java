class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean valid = true;
        for (int i = 0; i < 3 && valid; i++) {
            for (int j = 0; j < 3 && valid; j++) {
                valid = valid 
                            && isRowValid(board[i*3+j])
                            && isRowValid(columnToRow(board, i*3+j))
                            && isRowValid(squareToRow(board, i*3, j*3));
            }
        }
        return valid;
    }

    boolean isRowValid(char[] row) {
        int[] ocurrencies = new int[9];
        for (char c : row) {
            if(c != '.' && ++ocurrencies[c - '1'] > 1) {
                return false;
            }
        }
        return true;
    }

    char[] columnToRow(char[][] board, int column) {
        char[] row = new char[9];
        for (int i = 0; i < 9; i++) {
            row[i] = board[i][column];
        }
        return row;
    }

    char[] squareToRow(char[][] board, int row, int column) {
        int ind = 0;
        char[] res = new char[9];
        for (int i = row; i < row+3; i++) {
            for (int j = column; j < column+3; j++) {
                res[ind++] = board[i][j];
            }
        }
        return res;
    }
}

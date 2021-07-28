package algo.programers_test;

public class FindBiggestSquare {
    public void run() {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        int result = solution(board);
        System.out.println("result: " + result);
    }

    public int solution(int[][] board) {
        int maxLength = 0;
        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            Width width = getWidth(row);
            Height height = getHeight(board, i, width.getStartIdx());
            Square square = new Square(width,height);
            if(square.length > maxLength) {
                maxLength = square.length;
            }
        }
        return maxLength*maxLength;
    }

    private Width getWidth(int[] row) {
        int cnt = 0;
        int max = 0;
        int startIdx = 0;

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                if (cnt > max) {
                    max = cnt;
                }
                cnt = 0;
                continue;
            }
            if (cnt == 0) {
                startIdx = i;
            }
            cnt++;
        }

        if (cnt > max) {
            max = cnt;
        }

        return new Width(startIdx, max);
    }

    private Height getHeight(int[][] board, int rowNo, int idx) {
        int cnt = 0;
        int max = 0;
        int startIdx = 0;

        for (int i = rowNo; i < board.length; i++) {
            if (board[i][idx] == 0) {
                if (cnt > max) {
                    max = cnt;
                }
                cnt = 0;
                continue;
            }
            if (cnt == 0) {
                startIdx = i;
            }
            cnt++;
        }

        if (cnt > max) {
            max = cnt;
        }

        return new Height(startIdx, max);
    }


    class Square {
        private int width;
        private int height;
        private int length;

        public Square(Width width, Height height) {
            if (width.getLength() > height.getLength()) {
                this.width = height.getLength();
                this.height = height.getLength();
                this.length = height.getLength();
                return;
            }
            this.width = width.getLength();
            this.height = width.getLength();
            this.length = width.getLength();
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getLength() {
            return length;
        }
    }

    class Width {
        private int startIdx;
        private int length;

        public Width(int startIdx, int length) {
            this.startIdx = startIdx;
            this.length = length;
        }

        public int getStartIdx() {
            return startIdx;
        }

        public int getLength() {
            return length;
        }
    }

    class Height {
        private int startIdx;
        private int length;

        public Height(int startIdx, int length) {
            this.startIdx = startIdx;
            this.length = length;
        }

        public int getStartIdx() {
            return startIdx;
        }

        public int getLength() {
            return length;
        }
    }
}

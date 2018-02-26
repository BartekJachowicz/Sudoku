import java.awt.*;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

public class ClassicSudoku extends JFrame{
    private static final int values[][] = {
            {8, 2, 0, 0, 9, 0, 6, 0, 0},
            {0, 3, 0, 7, 0, 5, 8, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0, 3},
            {2, 0, 7, 8, 0, 9, 5, 0, 4},
            {1, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 5, 9, 0, 3, 0, 6, 0},
            {0, 0, 6, 0, 2, 0, 0, 5, 7},
    };

    private static final short groups[][] = {
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
    };

    private static final short solution[][] = {
            {8, 2, 4, 3, 9, 1, 6, 7, 5},
            {6, 3, 1, 7, 4, 5, 8, 2, 9},
            {5, 7, 9, 6, 8, 2, 3, 4, 1},
            {4, 5, 8, 1, 6, 7, 2, 9, 3},
            {2, 6, 7, 8, 3, 9, 5, 1, 4},
            {1, 9, 3, 2, 5, 4, 7, 8, 6},
            {9, 4, 2, 5, 7, 6, 1, 3, 8},
            {7, 8, 5, 9, 1, 3, 4, 6, 2},
            {3, 1, 6, 4, 2, 8, 9, 5, 7},
    };

    private static final Color groupColors[] = {
            new Color(187, 92, 10),
            new Color(208, 203, 35),
            new Color(194, 145, 17),
            new Color(25, 116, 21),
            new Color(17, 163, 187),
            new Color(49, 189, 12),
            new Color(149, 117, 198),
            new Color(187, 55, 149),
            new Color(187, 143, 179),
    };

    public ClassicSudoku() {
        initialize();
        setTitle("ClassicSudoku");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(630,630);
        setLayout(new GridLayout(9, 9));
        setResizable(false);
        setVisible(true);
    }

    private void initialize(){
        Board board = new Board(9);
        Field f;
        for(int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (values[i][j] == 0)
                    f = new Field(board, i, j, groups[i][j]);
                else
                    f = new Field(values[i][j], board, i, j, groups[i][j]);

                f.setFieldProperties(groupColors[groups[i][j] - 1]);

                board.setField(f);

                f.setBorder(new MatteBorder(
                            i == 0 ? 3 : 0,
                            j == 0 ? 3 : 0,
                            i % 3 == 2 ? 3 : 1,
                            j % 3 == 2 ? 3 : 1,
                        Color.BLACK));
                this.add(f);
            }
        }
    }
}

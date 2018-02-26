import java.awt.*;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

public class BigPlusSudoku extends JFrame{
    private static final int values[][] = {
            {2, 0, 0, 0, 0, 0},
            {0, 5, 0, 0, 1, 0},
            {0, 0, 4, 0, 0, 0},
            {0, 0, 0, 3, 0, 0},
            {0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 1},
    };

    private static final short groups[][] = {
            {1, 1, 1, 2, 2, 2},
            {1, 1, 3, 3, 2, 2},
            {1, 3, 3, 4, 4, 2},
            {5, 3, 3, 4, 4, 6},
            {5, 5, 4, 4, 6, 6},
            {5, 5, 5, 6, 6, 6},
    };

    private static final short solution[][] = {
            {2, 3, 1, 5, 6, 4},
            {4, 5, 3, 6, 1, 2},
            {6, 1, 4, 2, 5, 3},
            {1, 2, 5, 3, 4, 6},
            {3, 4, 6, 1, 2, 5},
            {5, 6, 2, 4, 3, 1},
    };

    private static final Color groupColors[] = {
            new Color(194, 145, 17),
            new Color(25, 116, 21),
            new Color(187, 92, 10),
            new Color(208, 203, 35),
            new Color(49, 189, 12),
            new Color(17, 163, 187),
    };

    public BigPlusSudoku() {
        initialize();
        setTitle("BigPlusSudoku");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(630,630);
        setLayout(new GridLayout(6, 6));
        setResizable(false);
        setVisible(true);
    }

    private void initialize(){
        Board board = new Board(6);
        Field f;
        for(int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (values[i][j] == 0)
                    f = new Field(board, i, j, groups[i][j]);
                else
                    f = new Field(values[i][j], board, i, j, groups[i][j]);

                f.setFieldProperties(groupColors[groups[i][j] - 1]);

                board.setField(f);

                f.setBorder(new MatteBorder(
                        i == 0 ? 6 : (groups[i - 1][j] != groups[i][j] ? 3 : 1), j == 0 ? 6 : (groups[i][j - 1] != groups[i][j] ? 3 : 1),
                        i == 5 ? 6 : (groups[i + 1][j] != groups[i][j] ? 3 : 1), j == 5 ? 6 : (groups[i][j + 1] != groups[i][j] ? 3 : 1),
                        Color.BLACK));
                this.add(f);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import javax.swing.border.MatteBorder;

public class HardSudoku extends JFrame{

    private static final int values[][] = {
            {1, 0, 0, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 4, 0},
            {0, 0, 0, 0, 5},
    };

    private static final short groups[][] = {
            {1, 1, 2, 2, 2},
            {1, 1, 2, 3, 2},
            {4, 1, 3, 3, 3},
            {4, 4, 4, 3, 5},
            {4, 5, 5, 5, 5},
    };

    private static final short solution[][] = {
            {1, 4, 5, 2, 3},
            {3, 2, 1, 5, 4},
            {4, 5, 2, 3, 1},
            {5, 1, 3, 4, 2},
            {2, 3, 4, 1, 5},
    };

    private static final Color groupColors[] = {
            new Color(25, 116, 21),
            new Color(194, 145, 17),
            new Color(187, 92, 10),
            new Color(208, 203, 35),
            new Color(49, 189, 12),
    };

    public HardSudoku() {
        initialize();
        setTitle("HardSudoku");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(630,630);
        setLayout(new GridLayout(5, 5));
        setResizable(false);
        setVisible(true);
    }

    private void initialize(){
        Board board = new Board(5);
        Field f;
        for(int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (values[i][j] == 0)
                    f = new Field(board, i, j, groups[i][j]);
                else
                    f = new Field(values[i][j], board, i, j, groups[i][j]);

                f.setFieldProperties(groupColors[groups[i][j] - 1]);
                board.setField(f);
                f.setBorder(new MatteBorder(
                        i == 0 ? 6 : (groups[i - 1][j] != groups[i][j] ? 3 : 1),
                        j == 0 ? 6 : (groups[i][j - 1] != groups[i][j] ? 3 : 1),
                        i == 4 ? 6 : (groups[i + 1][j] != groups[i][j] ? 3 : 1),
                        j == 4 ? 6 : (groups[i][j + 1] != groups[i][j] ? 3 : 1),
                        Color.BLACK));
                this.add(f);
            }
        }
    }
}

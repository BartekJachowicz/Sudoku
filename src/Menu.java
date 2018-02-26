import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Menu {
    private JPanel panel;
    private JButton BigPlusSudoku;
    private JButton HardSudoku;
    private JButton ClassicSudoku;

    public Menu() {
        BigPlusSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BigPlusSudoku();
            }
        });
        ClassicSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassicSudoku();
            }
        });
        HardSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HardSudoku();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku: Menu");
        frame.setContentPane(new Menu().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
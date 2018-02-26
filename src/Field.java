import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Field extends JTextField implements Serializable{
    private int value;
    private boolean generated;
    private boolean wrong;
    private Board board;
    private int row;
    private int column;
    private int group;
    private Color color;
    private int lastCorrectValue;


    public Field(Board b, int r, int c, int g){
        value = 0;
        generated = false;
        wrong = false;
        board = b;
        row = r;
        column = c;
        group = g;
        lastCorrectValue = 0;
    }
    public Field(int v, Board b, int r, int c, int g){
        value = v;
        generated = true;
        wrong = false;
        board = b;
        row = r;
        column = c;
        group = g;
        lastCorrectValue = 0;
    }

    public void setFieldProperties(Color c){
        color = c;
        setBackground(color);
        setHorizontalAlignment(JTextField.CENTER);
        if(this.isGenerated()) {
            setFont(new Font("Arial", Font.BOLD, 32));
            setEditable(false);
            setText(getValue() + "");
        }
        else{
            setFont(new Font("Arial", Font.PLAIN, 32));
            setForeground(Color.BLUE);
            setEditable(true);
            setText("");
        }
    }

    public int getValue(){
        return value;
    }
    public void setValue(int v){
        value = v;
    }

    public boolean isGenerated(){
        return generated;
    }
    public void setGenerated(boolean g){
        generated = g;
    }

    public boolean isWrong(){
        return wrong;
    }
    public void setWrong(boolean w){
        wrong = w;
        if(!w) setBackground(color);
        else setBackground(new Color(188, 59, 61));
    }

    public int getRow(){
        return row;
    }
    public void setRow(int r){
        row = r;
    }

    public int getColumn(){
        return column;
    }
    public void setColumn(int c){
        column = c;
    }

    public int getGroup(){
        return group;
    }
    public void setGroup(int g){
        group = g;
    }

    public Board getBoard(){
        return board;
    }
    public void setBoard(Board b){
        board = b;
    }

    public Color getColor(){ return color; }
    public void setColor(Color c){ color = c; }

    public int getLastCorrectValue(){ return lastCorrectValue; }
    public void setLastCorrectValue(int lcv) {lastCorrectValue = lcv; }

    public Set<Field> checkConflict(int v){
        Set<Field> tocheck = this.getBoard().getConnectedFields(this);
        Set<Field> conflicted = new HashSet<>();

        for(Field f: tocheck){
            if(f.getValue() == v)
                conflicted.add(f);
        }
        return conflicted;
    }

    public void setEmpty(){
        setValue(0);
        setText("");
        setWrong(false);
        setLastCorrectValue(0);
        Set<Field> conn = this.getBoard().getConnectedFields(this);
        for(Field ff : conn){
            Set<Field> s = ff.checkConflict(ff.getValue());
            if(s.isEmpty())
                ff.setWrong(false);
        }
    }
}

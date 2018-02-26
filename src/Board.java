import java.awt.event.*;
import java.beans.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Board implements Serializable{
    private int size;
    private Field fields[][];

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public Board(int s){
        size = s;
        fields = new Field[size][size];
        PropertyChangeListener pl = new PropertyListener();
        pcs.addPropertyChangeListener(pl);
        VetoableChangeListener vl = new VetoableListener();
        vcs.addVetoableChangeListener(vl);
    }

    public int getSize(){
        return size;
    }

    public void setField(Field f) {
        f.addActionListener(e -> {
            if(e.getActionCommand() == null || Objects.equals(e.getActionCommand(), ""))
                f.setEmpty();
            else {
                try {
                    vcs.fireVetoableChange(new PropertyChangeEvent(f, e.getActionCommand(), null, null));
                    pcs.firePropertyChange(new PropertyChangeEvent(f, e.getActionCommand(), null, null));
                } catch (PropertyVetoException e1) {
                    System.out.println(e1.getMessage());

                    if(!e1.getMessage().startsWith("Fields value")) {
                        f.setEmpty();
                    }
                    else if(e1.getMessage().equals("Fields values conflict.")){
                        pcs.firePropertyChange(new PropertyChangeEvent(f, e.getActionCommand(), null, null));
                    }
                    else{
                        f.setValue(f.getLastCorrectValue());
                        f.setText((f.getLastCorrectValue() == 0 ? "" : f.getLastCorrectValue() + ""));
                    }
                }
            }
        });

        f.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                f.getActionListeners()[0].actionPerformed(new ActionEvent(f, ActionEvent.ACTION_PERFORMED, f.getText()));
            }
        });
        fields[f.getRow()][f.getColumn()] = f;
    }

    public Set<Field> getConnectedFields(Field f){
        Set<Field> conn = new HashSet<>();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(fields[i][j].getRow() == f.getRow() && fields[i][j].getColumn() == f.getColumn())
                    continue;
                if(fields[i][j].getGroup() == f.getGroup())
                    conn.add(fields[i][j]);
                else if(fields[i][j].getRow() == f.getRow())
                    conn.add(fields[i][j]);
                else if(fields[i][j].getColumn() == f.getColumn())
                    conn.add(fields[i][j]);
            }
        }
        return conn;
    }
}
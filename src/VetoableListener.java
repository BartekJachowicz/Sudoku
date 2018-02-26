import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Set;

public class VetoableListener implements VetoableChangeListener{

    @Override
    public void vetoableChange(PropertyChangeEvent pce) throws PropertyVetoException {
        String sv = pce.getPropertyName();
        Field f = (Field) pce.getSource();
        int size = f.getBoard().getSize();
        int v;

        try {
            v = Integer.parseInt(sv);
        }
        catch (NumberFormatException e){
            throw new PropertyVetoException(sv + " is not a number", pce);
        }

        if(v > size || v <= 0) {
            throw new PropertyVetoException("Incorrect number: range from 1 to " + size + ".", pce);
        }

        Set<Field> conflict = f.checkConflict(v);

        if(conflict != null && !conflict.isEmpty()) {
            for(Field ffd: conflict)
                if(ffd.isGenerated())
                    throw new PropertyVetoException("Fields value conflict with generated filed.", pce);

            throw new PropertyVetoException("Fields values conflict.", pce);
        }
    }
}

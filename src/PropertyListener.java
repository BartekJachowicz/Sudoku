import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

public class PropertyListener implements PropertyChangeListener{

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Field f = (Field) pce.getSource();
        int value = Integer.parseInt(pce.getPropertyName());
        f.setText(value + "");
        f.setValue(value);
        f.setLastCorrectValue(f.getValue());

        Set<Field> conflict = f.checkConflict(f.getValue());

        if(conflict != null && !conflict.isEmpty()){
            f.setWrong(true);
            for(Field ff: conflict)
                ff.setWrong(true);
        }

        if(conflict != null){
            if(conflict.isEmpty())
                f.setWrong(false);
            Set<Field> conn = f.getBoard().getConnectedFields(f);
            for(Field ff : conn){
                Set<Field> s = ff.checkConflict(ff.getValue());
                if(s.isEmpty())
                    ff.setWrong(false);
            }
        }
    }
}

package mybeans.data_sheet_tablet;

import java.util.EventListener;

public interface ChangeListener extends EventListener {
    public void dataChanged(ChangeEvent e);
}
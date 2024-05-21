package mybeans.data_sheet_tablet;

import java.util.EventObject;

public class ChangeEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    public ChangeEvent(Object source) {
        super(source);
    }
}
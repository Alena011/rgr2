package mybeans.data_sheet_tablet;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final int columnCount = 3;
    private int rowCount = 1;
    private Datasheet dataSheet = null;

    private final String[] columnNames = {"Date", "X Value", "Y Value"};

    public TableModel(){
        dataSheet = new Datasheet();
        rowCount = dataSheet.size();
    }

    public Datasheet getDataSheet(){
        return dataSheet;
    }

    public void setDataSheet(Datasheet dataSheet){
        this.dataSheet = dataSheet;
        rowCount = this.dataSheet.size();
        fireDataSheetChange();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataSheet != null) {
            if (columnIndex == 0)
                return dataSheet.getDataItem(rowIndex).getDate();
            else if (columnIndex == 1)
                return dataSheet.getDataItem(rowIndex).getX();
            else if (columnIndex == 2)
                return dataSheet.getDataItem(rowIndex).getY();
        }
        return null;
    }


    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            double d;
            if (dataSheet != null) {
                if (columnIndex == 0) {
                    dataSheet.getDataItem(rowIndex).setDate((String) value);
                } else if (columnIndex == 1) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDataItem(rowIndex).setX(d);
                } else if (columnIndex == 2) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDataItem(rowIndex).setY(d);
                }
            }
            fireDataSheetChange();
        } catch (Exception ignored) {}
    }


    public  void setRowCount(int rowCount){
        if(rowCount > 0) this.rowCount = rowCount;
    }

    private ArrayList<ChangeListener> listenerList = new ArrayList<>();

    private ChangeEvent event = new ChangeEvent(this);

    public void addDataSheetChangeListener(ChangeListener l) {
        listenerList.add(l);
    }

    public void removeDataSheetChangeListener(ChangeListener l) {
        listenerList.remove(l);
    }

    protected void fireDataSheetChange() {
        for (ChangeListener changeListener : listenerList) changeListener.dataChanged(event);
    }

}
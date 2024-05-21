package mybeans.data_sheet_tablet;

import javax.swing.*;

public class Tablet {
    private JButton addButton;
    private JButton delButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel rootPane;
    private TableModel tableModel;

    private void createUIComponents() {

        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tableModel = new TableModel();
        table = new JTable();
        table.setModel(tableModel);

        addButton = new JButton();
        addButton.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            tableModel.getDataSheet().addDataItem(new Data());
            table.revalidate();
            tableModel.fireDataSheetChange();
        });

        delButton = new JButton();
        delButton.addActionListener(e -> {
            if (tableModel.getRowCount() > 1) {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                tableModel.getDataSheet().removeDataItem(tableModel.getDataSheet().size() - 1);
                table.revalidate();
            }
            tableModel.fireDataSheetChange();
        });

    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
        table.revalidate();
    }

    public void revalidate() {
        if (table != null) table.revalidate();
    }

}
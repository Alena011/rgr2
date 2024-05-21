package mybeans;

import mybeans.data_sheet_tablet.Data;
import mybeans.data_sheet_tablet.Datasheet;
import mybeans.data_sheet_tablet.XmlSaver;
import mybeans.data_sheet_graph.Graph;
import mybeans.data_sheet_tablet.Tablet;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private final JFileChooser fileChooser = new JFileChooser();
    private JPanel contentPane;
    private Datasheet dataSheet;
    private Graph graph;
    private Tablet dataSheetTable;
    private JButton readButton;
    private JButton saveButton;
    private JButton clearButton;
    private JButton exitButton;

    public MainFrame() {
        setContentPane(contentPane);
        dataSheet = new Datasheet();
        dataSheet.addDataItem(new Data());
        graph.setDataSheet(dataSheet);
        dataSheetTable.getTableModel().setDataSheet(dataSheet);
        fileChooser.setCurrentDirectory(new java.io.File("."));



        exitButton.addActionListener(e -> {
            dispose();
        });

        clearButton.addActionListener(e -> {
            dataSheet = new Datasheet();
            dataSheet.addDataItem(new Data());
            dataSheetTable.getTableModel().setDataSheet(dataSheet);
            dataSheetTable.revalidate();
            graph.setDataSheet(dataSheet);
        });

        saveButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                try {
                    XmlSaver.marshalDataToXML(fileName,dataSheet);
                    JOptionPane.showMessageDialog(null,
                            "File " + fileName.trim() + " saved!", "Результат збережено",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (JAXBException jaxbException) {
                    JOptionPane.showMessageDialog(null,
                            "File " + fileName.trim() + "not saved, error!!", "Результати не збереглися",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        readButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                try {
                    dataSheet = XmlSaver.unmarshalOutXMLs(fileName);
                } catch (JAXBException jaxbException) {
                    jaxbException.printStackTrace();
                }
                dataSheetTable.getTableModel().setDataSheet(dataSheet);
                dataSheetTable.revalidate();
                dataSheetTable.revalidate();
                graph.setDataSheet(dataSheet);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        dataSheetTable.getTableModel().addDataSheetChangeListener(e -> {
            graph.revalidate();
            graph.repaint();
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.pack();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
package view;

import model.StandardDepartment;

import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class DptJDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;

    public DptJDialog(ActionListener actionListener, StandardDepartment standardDepartment) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> dispose());
        buttonOK.setActionCommand("Add Dpt");
        buttonOK.addActionListener(actionListener);

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }

    private void onCancel() {
        // ferme la fenetre sans kill
        dispose();
    }

    StandardDepartment getDptInputs(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    }
}

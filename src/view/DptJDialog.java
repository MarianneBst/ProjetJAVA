package view;

import model.Manager;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DptJDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField departmentNameTextField;
    private JTextField firstNameTextField;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JComboBox startHourComboBox;
    private JComboBox startMinuteComboBox;
    private JComboBox endHourComboBox;
    private JComboBox endMinuteComboBox;

    public DptJDialog(ActionListener actionListener, StandardDepartment standardDepartment) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        pack();

        buttonOK.setActionCommand("Add Dpt");
        buttonOK.addActionListener(actionListener);
        buttonOK.addActionListener(e -> dispose());

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String startHourString = "1900-01-01 " + startHourComboBox.getSelectedItem()+":" +
                startMinuteComboBox.getSelectedItem();
        LocalDateTime startTime = LocalDateTime.parse(startHourString, formatter);
        String endHourString = "1900-01-01 " + endHourComboBox.getSelectedItem()+":" +
                endMinuteComboBox.getSelectedItem();
        LocalDateTime endTime = LocalDateTime.parse(endHourString, formatter);

        Manager manager = new Manager(nameTextField.getText(), firstNameTextField.getText(),
                startTime, endTime, emailTextField.getText());

        return new StandardDepartment(departmentNameTextField.getText(), manager);
    }
}

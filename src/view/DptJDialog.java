package view;

import model.Manager;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Dpt j dialog.
 */
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
    private JPanel managerPanel;
    private StandardDepartment standardDepartment;

    /**
     * Instantiates a new Dpt j dialog.
     *
     * @param actionListener     the action listener
     * @param standardDepartment the standard department
     */
    public DptJDialog(ActionListener actionListener, StandardDepartment standardDepartment) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.standardDepartment = standardDepartment;

        if (standardDepartment == null){
            setTitle("Add department");
        }
        else {
            setTitle("Modify department");
            managerPanel.setVisible(false);
            departmentNameTextField.setText(standardDepartment.getName());
        }

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        pack();

        if (standardDepartment == null){
            buttonOK.setActionCommand("Add Dpt");
        }
        else {
            buttonOK.setActionCommand("Modify Dpt");
        }
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

    /**
     * Gets dpt inputs.
     *
     * @return the dpt inputs
     * @throws IllegalArgumentException the illegal argument exception
     */
    StandardDepartment getDptInputs() throws IllegalArgumentException{
        if ((nameTextField.getText().length() == 0 || firstNameTextField.getText().length() == 0
                || emailTextField.getText().length() == 0 || departmentNameTextField.getText().length() == 0) && standardDepartment == null){
            throw new IllegalArgumentException("You must fill all the textFields !");
        }

        if (standardDepartment != null){
            if (departmentNameTextField.getText().length() == 0){
                throw new IllegalArgumentException("You must enter a name !");
            }
            return new StandardDepartment(departmentNameTextField.getText(), new Manager());
        }

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

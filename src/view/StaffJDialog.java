package view;

import model.Employee;
import model.Manager;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The type Staff j dialog.
 */
public class StaffJDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldName;
    private JTextField textFieldFirstname;
    private JComboBox comboBoxHourStartHour;
    private JComboBox comboBoxMinStartHour;
    private JComboBox comboBoxHourEndHour;
    private JComboBox comboBoxMinEndHour;
    private JComboBox<StandardDepartment> comboBoxDpt;

    /**
     * Instantiates a new Staff j dialog.
     *
     * @param actionListener the action listener
     * @param employee       the employee
     * @param departmentList the department list
     */
    public StaffJDialog(ActionListener actionListener, Employee employee, ArrayList<StandardDepartment> departmentList) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);//?

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //si on clic sur le bouton ok
        buttonOK.setActionCommand("Add Employee");
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

        for(StandardDepartment standardDepartment : departmentList){
            comboBoxDpt.addItem(standardDepartment);
        }

        if(employee != null){
            if (employee.getClass() == Manager.class){
                comboBoxDpt.setEnabled(false);
            }
            buttonOK.setActionCommand("Modif Employee");
            textFieldName.setText(employee.getName());
            textFieldFirstname.setText(employee.getFirstName());
            comboBoxHourStartHour.setSelectedItem(employee.getStartHour().getHour());
            comboBoxMinStartHour.setSelectedItem(employee.getStartHour().getMinute());
            comboBoxHourEndHour.setSelectedItem(employee.getEndHour().getHour());
            comboBoxMinEndHour.setSelectedItem(employee.getEndHour().getMinute());
        }

        pack();
    }

    private void onCancel() {
        // ferme la fenetre sans kill
        dispose();
    }

    /**
     * Gets employee inputs.
     *
     * @return the employee inputs
     * @throws IllegalArgumentException the illegal argument exception
     */
    Employee getEmployeeInputs()throws IllegalArgumentException{
        if (textFieldName.getText().length() == 0 || textFieldFirstname.getText().length() == 0){
            throw new IllegalArgumentException("You must fill all the textFields !");
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //on récupère la start hour selon le format formatter
        String startHourString = "1900-01-01 " + comboBoxHourStartHour.getSelectedItem()+":" +
                comboBoxMinStartHour.getSelectedItem();

        LocalDateTime startTime = LocalDateTime.parse(startHourString, formatter);

        // on récupère la end hour
        String endHourString = "1900-01-01 " + comboBoxHourEndHour.getSelectedItem()+":" +
                comboBoxMinEndHour.getSelectedItem();

        LocalDateTime endTime = LocalDateTime.parse(endHourString, formatter);


        //on recupère le department
        StandardDepartment department = comboBoxDpt.getItemAt(comboBoxDpt.getSelectedIndex());

        //on créé l'employer
        Employee employee = new Employee(textFieldName.getText(), textFieldFirstname.getText(),
                startTime, endTime, department);



        return employee;
    }
}
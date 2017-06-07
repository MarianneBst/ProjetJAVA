package view;

import model.Company;
import model.Employee;
import model.Manager;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Marianne
 * on 29/04/2017.
 */
public class MainView extends JFrame implements Observer {
    private JPanel mainPanel;
    private JButton exitButton;
    private JTabbedPane tabbedPane1;
    private StaffManagementJPanel staffManagementJPanel;
    private DptManagementJPanel dptManagementJPanel;
    private ArrayList<StandardDepartment> standardDepartmentList;

    //constructor,
    public MainView(ActionListener actionListener, ArrayList<StandardDepartment> standardDepartmentList){
        super("Start");

        // def principal panel
        setContentPane(mainPanel);

        if(actionListener != null){
            registerListener(actionListener);
        }

        this.standardDepartmentList = standardDepartmentList;

        // création des onglets
        staffManagementJPanel = new StaffManagementJPanel(actionListener, standardDepartmentList);
        dptManagementJPanel = new DptManagementJPanel(actionListener);

        //ajout des onglets dans le panel qui doit les contenir
        tabbedPane1.add(staffManagementJPanel);
        tabbedPane1.add(dptManagementJPanel);

        //set minimum size and set the position
        improvePlacement();

        // action when we click on exitButton
        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        //pour que la fenetre s'affiche à l'ecran
        setVisible(true);

        // ce qu'il se passe quand on appuie sur la croix
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // set minimum size and set the position
    protected void improvePlacement() {
        //Minimize the frame's size and freeze the minimum size
        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    //error icon
    public void showError(String message) {
        // custom title, parentComponent ?
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    // all button or frame where it action use the model
    public void registerListener(ActionListener actionListener) {

    }

    public StandardDepartment getDepartmentCreated(){
        return dptManagementJPanel.getDptJDialog().getDptInputs();
    }

    public Employee getEmployeeCreated(){
        return staffManagementJPanel.getEmployeeJDialog().getEmployeeInputs();
    }

    //appeler quand model changer (avec setChanged -> qd controller appel model)
    @Override
    public void update(Observable o, Object arg) {
        Company company = (Company) o;
        dptManagementJPanel.myUpdate(company);
        staffManagementJPanel.myUpdate(company);
    }

    public Employee getEmployeeToModify() {
        return staffManagementJPanel.getSelectedEmployee();
    }

    public ArrayList<Employee> getEmployeesSelected(){
        return staffManagementJPanel.getSelectedEmployees();
    }
}

package view;

import model.Company;
import model.Employee;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
    private GlobalCheckHistoryJPanel globalCheckHistoryJPanel;
    private HIYDJPanel hiydjPanel;
    private ArrayList<StandardDepartment> standardDepartmentList;

    /**
     * Instantiates a new Main view.
     *
     * @param actionListener         the action listener
     * @param standardDepartmentList the standard department list
     */
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
        globalCheckHistoryJPanel = new GlobalCheckHistoryJPanel();
        hiydjPanel = new HIYDJPanel();

        //ajout des onglets dans le panel qui doit les contenir
        tabbedPane1.add(dptManagementJPanel);
        tabbedPane1.add(staffManagementJPanel);
        tabbedPane1.add(globalCheckHistoryJPanel);
        tabbedPane1.add(hiydjPanel);

        //Menu
         JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        // Sous-menus
        JMenuItem menuOpen = new JMenuItem("Open");
        menuOpen.addActionListener(actionListener);
        menuFile.add(menuOpen);
        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.addActionListener(actionListener);
        menuFile.add(menuSave);

        setJMenuBar(menuBar);

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

    /**
     * Improve placement.
     */
// set minimum size and set the position
    protected void improvePlacement() {
        //Minimize the frame's size and freeze the minimum size
        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    /**
     * Show error.
     *
     * @param message the message
     */
//error icon
    public void showError(String message) {
        // custom title, parentComponent ?
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Register listener.
     *
     * @param actionListener the action listener
     */
// all button or frame where it action use the model
    public void registerListener(ActionListener actionListener) {

    }

    /**
     * Get department created standard department.
     *
     * @return the standard department
     */
    public StandardDepartment getDepartmentCreated(){
        return dptManagementJPanel.getDptJDialog().getDptInputs();
    }

    /**
     * Get employee created employee.
     *
     * @return the employee
     */
    public Employee getEmployeeCreated(){
        return staffManagementJPanel.getEmployeeJDialog().getEmployeeInputs();
    }

    //appeler quand model changer (avec setChanged -> qd controller appel model)
    @Override
    public void update(Observable o, Object arg) {
        Company company = (Company) o;
        dptManagementJPanel.myUpdate(company);
        staffManagementJPanel.myUpdate(company);
        globalCheckHistoryJPanel.myUpdate(company);
        hiydjPanel.myUpdate(company);
    }

    /**
     * Gets employee to modify.
     *
     * @return the employee to modify
     */
    public Employee getEmployeeToModify() {
        return staffManagementJPanel.getSelectedEmployee();
    }

    /**
     * Get employees selected array list.
     *
     * @return the array list
     */
    public ArrayList<Employee> getEmployeesSelected(){
        return staffManagementJPanel.getSelectedEmployees();
    }

    /**
     * Gets dpt created.
     *
     * @return the dpt created
     */
    public StandardDepartment getDptCreated() {
        return dptManagementJPanel.getDptJDialog().getDptInputs();
    }

    /**
     * Gets dpt to modify.
     *
     * @return the dpt to modify
     */
    public StandardDepartment getDptToModify() {
        return dptManagementJPanel.getSelectedDpt();
    }

    /**
     * Gets dpts selected.
     *
     * @return the dpts selected
     */
    public ArrayList<StandardDepartment> getDptsSelected() {
        return dptManagementJPanel.getSelectedDpts();
    }
}

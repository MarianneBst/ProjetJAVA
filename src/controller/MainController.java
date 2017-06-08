package controller;

import TCPCommunication.TCPServer;
import au.com.bytecode.opencsv.CSVWriter;
import model.Company;
import model.Employee;
import model.StandardDepartment;
import view.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Marianne
 * on 30/04/2017.
 */
public class MainController implements ActionListener {
    private Company company;
    private MainView mainView;
    private TCPServer serverSocket;

    /**
     * Instantiates a new Main controller.
     *
     * @param company the company
     */
    public MainController(Company company) {
        this.company = company;
        this.mainView = new MainView(this, company.getStandardDepartmentList());
        company.addObserver(mainView);
        this.serverSocket = new TCPServer(company);
        this.serverSocket.run();
    }


    // call when action in button
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            switch (e.getActionCommand()){
                case "Add Dpt":
                    company.addStandardDepartment(mainView.getDepartmentCreated());
                    break;
                case "Add Employee":
                    company.addEmployeeIntoStandardDpt(mainView.getEmployeeCreated());
                    break;
                case "Modif Employee":
                    company.modifyEmployee(mainView.getEmployeeToModify(), mainView.getEmployeeCreated());
                    break;
                case "Remove Employee":
                    for (Employee employee : mainView.getEmployeesSelected()) {
                        company.removeEmployeeFromStandardDpt(employee);
                    }
                    break;
                case "Modify Dpt":
                    company.modifyDpt(mainView.getDptToModify(), mainView.getDptCreated());
                    break;
                case "Remove Dpt":
                    for (StandardDepartment standardDepartment : mainView.getDptsSelected()) {
                        company.deleteStandardDepartment(standardDepartment);
                    }
                    break;
                case "Open":
                    company = onOpen();
                    if (company != null) {
                        company.addObserver(mainView);
                    }
                    serverSocket.setCompany(company);
                    mainView.update(company, null);
                    break;
                case "Save":
                    onSave();
                    break;
                case "Import":
                    onImport();
                    break;
                case "Export":
                    onExport();
                    break;
            }
            company.notifyObservers();
        }
        catch (Exception exc){
            //custom title, error icon
            JOptionPane.showMessageDialog(mainView,
                    exc.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onExport() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter barFilter = new FileNameExtensionFilter(
                "CVS file (*.csv)", "csv");
        fileChooser.setFileFilter(barFilter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(file.getPath() + ".csv"));

                //Create record
                for (Employee employee : company.getOnlyEmployees()) {
                    String [] record = employee.getRecord();
                    //Write the record to file
                    writer.writeNext(record);
                }
                //close the writer
                writer.close();
            }catch (NoSuchElementException noSuchElementException) {
                System.err.println("Invalid input.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onImport() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter barFilter = new FileNameExtensionFilter(
                "CSV file (*.csv)", "csv");
        fileChooser.setFileFilter(barFilter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ArrayList<EmployeeDAO> employeeDAOS = EmployeeCSV.findEmployees(file);
            for (EmployeeDAO employeeDAO : employeeDAOS) {
                company.addEmployeeIntoStandardDpt(new Employee(employeeDAO.getName(), employeeDAO.getFirstName(), employeeDAO.getId(), employeeDAO.getStartHour(), employeeDAO.getEndHour(), employeeDAO.getCreditHour(), company.getStandardDepartmentByName(employeeDAO.getStandardDepartmentName())));
            }
        }
    }

    private void onSave(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter barFilter = new FileNameExtensionFilter(
                "company files (*.cmp)", "cmp");
        fileChooser.setFileFilter(barFilter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ObjectOutputStream outStream = null;
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(file.getPath()+".cmp");
                outStream = new ObjectOutputStream(fileOutputStream);
                outStream.writeObject(company);
            } catch (IOException ioException) {
                System.out.println("Error");
            } catch (NoSuchElementException noSuchElementException) {
                System.err.println("Invalid input.");
            } finally {
                try {
                    if (outStream != null)
                        outStream.close();
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                }
            }
        }
    }

    private Company onOpen(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter barFilter = new FileNameExtensionFilter(
                "company files (*.cmp)", "cmp");
        fileChooser.setFileFilter(barFilter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return loadFromFile(file);
        }
        return null;
    }

    private Company loadFromFile(File file){
        Company company = null;
        ObjectInputStream inputStream = null;
        try {
            FileInputStream fileInputStream=new FileInputStream(file.getPath());
            inputStream = new ObjectInputStream(fileInputStream);
            company = (Company) inputStream.readObject();
        } catch (EOFException eofException) {
            JOptionPane.showMessageDialog(mainView, "End of file", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException classNotFoundException) {
            JOptionPane.showMessageDialog(mainView, "Object creation failed.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(mainView, "Error opening file, check if your file has the good extension (*.cmp)...", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(mainView, "Error closing file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return company;
    }
}

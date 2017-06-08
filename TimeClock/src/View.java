import TCPCommunication.TCPClient;
import model.Employee;
import model.Tally;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The type View.
 */
public class View extends JFrame{


    private JButton checkInOutButton;
    private JComboBox<Employee> employeeComboBox;
    private JButton syncWithTheMainButton;
    private JLabel dateLabel;
    private JLabel clockLabel;
    private JPanel mainPanel;
    private JLabel myClockLabel;
    private DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel;
    private TCPClient clientSocket;


    /**
     * @author Marianne
     * @since 24/05/2017
     */
    private class ClockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter myClockFormat = DateTimeFormatter.ofPattern("HH:mm");
            clockLabel.setText(clockFormat.format(LocalDateTime.now()));
            dateLabel.setText(dateFormat.format(LocalDateTime.now()));

            // Arrondis
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int round = calendar.get(Calendar.MINUTE) % 15;
            calendar.add(Calendar.MINUTE, round < 8 ? -round : (15-round));
            calendar.set( Calendar.SECOND, 0 );
            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");

            myClockLabel.setText(format1.format(calendar.getTime()));
        }
    }

    /**
     * Instantiates a new View.
     */
    public View(){
        setContentPane(mainPanel);
        setTitle("Pointing module");
        setSize(500, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Timer timer=new Timer(1000, new ClockListener());
        clientSocket=new TCPClient();
        employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        employeeComboBox.setModel(employeeDefaultComboBoxModel);


        timer.start();
        clientSocket.start();


        syncWithTheMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSynch();
            }
        });
        checkInOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCheck();
            }
        });

        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void onSynch(){
        if (employeeComboBox.getItemCount()!=0){
            employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
            employeeComboBox.setModel(employeeDefaultComboBoxModel);
        }
        ArrayList<Employee> myEmployees=clientSocket.updateDatas();
        for (Employee employee:myEmployees) {
            employeeDefaultComboBoxModel.addElement(employee);
        }
        employeeComboBox.updateUI();

        pack();
    }

    private void onCheck(){
        int i;
        for(i=0; i<employeeDefaultComboBoxModel.getSize(); i++){
            if(employeeDefaultComboBoxModel.getElementAt(i).toString().equalsIgnoreCase(employeeComboBox.getSelectedItem().toString())){
                break;
            }
        }
        Employee employee = employeeDefaultComboBoxModel.getElementAt(i);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        Tally tally=new Tally(employee, LocalDateTime.parse(LocalDate.now() + myClockLabel.getText(), formatter));
        Tally tally=new Tally(employee, LocalDateTime.parse(LocalDate.now().format(formatter1) +" " + myClockLabel.getText(), formatter));
        clientSocket.sendData(tally);
    }
}

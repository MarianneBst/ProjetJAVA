package TCPCommunication;

import model.Company;
import model.Employee;
import model.Tally;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The type Tcp server.
 *
 * @author Mrianne
 * @since 30 /05/2017
 */
public class TCPServer implements Runnable{
    private static ServerSocket serverSocket;
    private Socket socket;
    private Company company;

    private boolean stillWorking = true;

    /**
     * Stop networking.
     */
    public void stopNetworking(){
        stillWorking = false;
    }


    /**
     * Instantiates a new Tcp server.
     *
     * @param company the company
     */
    public TCPServer(Company company){
        try {
            this.company = company;
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets company.
     *
     * @param company the company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Read datas tally.
     *
     * @return the tally
     */
    public Tally readDatas() {
        Tally tally = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            tally =(Tally) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tally;
    }

    /**
     * Send data.
     */
    public void sendData(){
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            ArrayList<Employee> employees = company.getAllEmployees();
            oos.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(stillWorking) {
            try {
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                if(is.read()==1){
                    sendData();
                }
                else{
                    company.addTally(readDatas());
                    company.notifyObservers();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Networking is going down now !");
    }
}

package TCPCommunication;

import model.Employee;
import model.Tally;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The type Tcp client.
 *
 * @author Marianne
 * @since 01 /06/2017
 */
public class TCPClient extends Thread{
    private Socket socket;

    private boolean work=true;

    /**
     * Stop networking.
     */
    public void stopNetworking() {
        work = false;
    }

    /**
     * Update datas array list.
     *
     * @return the array list
     */
    public ArrayList<Employee> updateDatas() {
        ArrayList<Employee> datas = new ArrayList<>();

        try {
            socket = new Socket("localhost",8000);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(1); //on écrit 1 pour demander un update des données.

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);

            datas = (ArrayList<Employee>) ois.readObject();
            socket.close();
        }
        catch(IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return datas;
    }

    /**
     * Send data.
     *
     * @param data the data
     */
    public void sendData(Tally data) {
        try{
            socket = new Socket("localhost",8000);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(0);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(data);

            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(work){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("TPC connection is going down now !");
    }
}

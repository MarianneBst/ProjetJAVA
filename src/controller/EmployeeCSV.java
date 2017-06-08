package controller;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marianne
 * on 08/06/2017.
 */
public class EmployeeCSV {

    private final static char SEPARATOR = ',';

    /**
     * Find employees array list.
     *
     * @param fileInputs the file inputs
     * @return the array list
     */
    static public ArrayList<EmployeeDAO> findEmployees(File fileInputs) {
        ArrayList<EmployeeDAO> employees = new ArrayList<>();
        FileReader fr;
        try {
            fr = new FileReader(fileInputs);

            CSVReader csvReader = new CSVReader(fr, SEPARATOR, '"');

            List<String[]> data = new ArrayList<>();

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                int size = nextLine.length;

                //Empty line
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }

                //Comment line
                if (debut.startsWith("#")) {
                    continue;
                }
                data.add(nextLine);
            }


            for (String[] oneData : data) {
                String name = oneData[0];
                String firstName = oneData[1];
                String idStr = oneData[2];
                String startHourStr = oneData[3];
                String endHourStr = oneData[4];
                String creditStr = oneData[5];
                String dpt = oneData[6];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                EmployeeDAO employeeDAO = new EmployeeDAO(name, firstName, UUID.fromString(idStr), LocalDateTime.parse("1900-01-01 " + startHourStr, formatter), LocalDateTime.parse("1900-01-01 " + endHourStr, formatter), Integer.parseInt(creditStr), dpt);
                employees.add(employeeDAO);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}



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
 * Created by Ronan
 * on 08/06/2017.
 */
public class EmployeeCSV {

    private final static char SEPARATOR = ',';

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

                // ligne vide
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }

                // ligne de commentaire
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

                EmployeeDAO employeeDAO = new EmployeeDAO(name, firstName, UUID.fromString(idStr), LocalDateTime.parse("1900-01-01 " + startHourStr, formatter), LocalDateTime.parse("1900-01-01 " + endHourStr, formatter), LocalDateTime.parse("1900-01-01 " + creditStr, formatter), dpt);
                employees.add(employeeDAO);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}



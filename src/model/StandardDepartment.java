package model;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class StandardDepartment extends VirtualDepartment{

    public StandardDepartment(String name, Manager manager) {
        super(name, manager);
    }

    @Override
    public String toString() {
        return "StandardDepartment" + super.toString();
    }



}

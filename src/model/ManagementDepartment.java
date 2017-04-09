package model;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class ManagementDepartment extends VirtualDepartment{

    public ManagementDepartment(Boss boss) {
        super("Management Department", boss);
    }

    @Override
    public String toString() {
        return "ManagementDepartment" + super.toString();
    }
}

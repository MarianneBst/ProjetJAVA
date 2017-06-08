package model;

import java.io.Serializable;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class ManagementDepartment extends VirtualDepartment implements Serializable {

    /**
     * Instantiates a new Management department.
     *
     * @param boss the boss
     */
    public ManagementDepartment(Boss boss) {
        super("Management Department", boss);
    }

    @Override
    public String toString() {
        return "ManagementDepartment" + super.toString();
    }
}

package Models;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Group {

    private Integer id;
    private String name;
    private String description;
    private Group parent;

    public Group(Integer id, String name, String description, Group parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }


}

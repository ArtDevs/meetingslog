package org.artdevs.meetingslog.core.model;

/**
 * Created by Slava on 24.01.2015.
 */
public class Group {
    private int id;
    private String name;
    private int owner_id;

    public Group(){
    }

    public Group(int id,String name, int owner_id){
        this.id=id;
        this.name=name;
        this.owner_id=owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return name;
    }
}


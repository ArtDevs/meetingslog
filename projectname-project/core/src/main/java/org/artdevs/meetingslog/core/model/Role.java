package org.artdevs.meetingslog.core.model;

import java.security.Permission;

/**
 * Created by Slava on 22.12.2014.
 */
public class Role {
    Integer id;
    String name;
    String description;
    Integer permissions;

    public Role() {}

    public Role(Integer id, String name, String description, Integer permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object obj) {
        return permissions==((Role)obj).permissions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append(" name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }
}

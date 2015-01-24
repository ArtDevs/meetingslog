package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Slava on 22.12.2014.
 */
@Component
public class RoleSqlDao implements RoleDAO {


    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

    private RowMapper<Role> roleRowMapper=new RowMapper<Role>(){
        public Role mapRow (ResultSet res,int rowNum)throws SQLException {
            return new Role(res.getInt("id"),
                            res.getString("name"),
                            res.getString("description"),
                            res.getInt("permissions")
            );
        }
    };


    @Override
    public List<Role> getAll() {
        final String qryStr="SELECT * FROM ml_roles";
        return namedParamTemplate.query(qryStr,roleRowMapper);
    }

    @Override
    public Role findById(int id) {
        final String qryStr="SELECT * FROM ml_roles WHERE id=:id";
        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("id",id);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, roleRowMapper);
        }catch (Exception e){return null;}

    }

    @Override
    public Role findByName(String name) {
        final String qryStr="SELECT * FROM ml_roles WHERE name=:name";
        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("name",name);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, roleRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public void insert(Role role) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("INSERT INTO ml_roles ");
        qryStrBuilder.append("(name,description,permissions)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:name,:description,:permissions)");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("name", role.getName());
        mapPars.put("description", role.getDescription());
        mapPars.put("permissions", role.getPermissions());

        namedParamTemplate.update(qryStr,mapPars);

        role.setId(findByName(role.getName()).getId());

    }

    @Override
    public void updateById(Role role) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_roles SET ");
        qryStrBuilder.append("(name,description,permissions)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:name,:description,:permissions)");
        qryStrBuilder.append(" WHERE id=:id");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", role.getId());
        mapPars.put("name", role.getName());
        mapPars.put("description", role.getDescription());
        mapPars.put("permissions", role.getPermissions());

        namedParamTemplate.update(qryStr,mapPars);

    }

    @Override
    public void updateByName(Role role) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_roles SET ");
        qryStrBuilder.append("(id,description,permissions)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:id,:description,:permissions)");
        qryStrBuilder.append(" WHERE name=:name");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", role.getId());
        mapPars.put("name", role.getName());
        mapPars.put("description", role.getDescription());
        mapPars.put("permissions", role.getPermissions());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeById(int id) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        final String qryStr="DELETE FROM ml_roles WHERE id=:id";

        namedParamTemplate.update(qryStr,mapPars);

    }

    @Override
    public void removeByName(String name) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("name",name);

        final String qryStr="DELETE FROM ml_roles WHERE name=:name";

        namedParamTemplate.update(qryStr,mapPars);

    }
}

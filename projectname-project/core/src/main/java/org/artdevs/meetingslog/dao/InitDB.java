package org.artdevs.meetingslog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Slava on 12.12.2014.
 */
//@Component
public class InitDB {
    @Autowired
    private DriverManagerDataSource dataSrc;

    private Resource rec;

    public void setRec(Resource rec) {
        this.rec = rec;
    }

    public void initialize(){
        try{

            InputStream iStream=rec.getInputStream();
            Scanner scan=new Scanner(iStream);
            StringBuilder sqlStr=new StringBuilder();

            while(scan.hasNext()){
                sqlStr.append(scan.nextLine()).append("\n");
            }
            scan.close();
            iStream.close();

            Connection conn=null;
            Statement query=null;

            try{
                conn=dataSrc.getConnection();
                query=conn.createStatement();
                query.execute(sqlStr.toString());

            }catch(SQLException exept){
                throw new RuntimeException("SQL error creating tables",exept);
            }finally {
                try {
                    if (conn != null)
                        conn.close();
                }catch(SQLException exept){}
            }

        }catch(IOException exept){
            throw new RuntimeException("Bad resource",exept);
        }
    }
}

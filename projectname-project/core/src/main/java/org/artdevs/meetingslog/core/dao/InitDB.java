package org.artdevs.meetingslog.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Slava on 22.12.2014.
 */

@Component
public class InitDB {
    @Autowired
    private DriverManagerDataSource dataSrc;

    private Resource rec;

    @Value("${jdbc.schema}")
    public void setRec(Resource rec) {
        this.rec = rec;
    }

    @PostConstruct
    public void initialize() throws SQLException {
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
                }catch(SQLException exept){
                    throw exept;
                }
            }

        }catch(IOException exept){
            throw new RuntimeException("Bad resource",exept);
        }
    }

}

package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA25 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                ArrayList<JsonObject> rows = new ArrayList<>();
                
                while (rs.next()) {
                    JsonObject json = new JsonObject();
                    
                    for (int i = 1; i <= columnCount; i++) {
                        json.put(rsmd.getColumnLabel(i), String.valueOf(rs.getObject(i)));
                    }
                    rows.add(json);
                }
                
                // Adds all rows to records
                for (int i = 0; i < rows.size(); i++) {
                    
                    JsonObject row = rows.get(i);
                    records.add(row);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}

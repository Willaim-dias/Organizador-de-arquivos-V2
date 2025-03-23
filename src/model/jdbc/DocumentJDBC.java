
package model.jdbc;

import config.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DocumentDao;
import model.entities.Document;

public class DocumentJDBC implements DocumentDao {

    private Connection conn;
    
    public DocumentJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Document obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO files(title, category, description, file) VALUES (?, ?, ?, ?)");
            st.setString(1, obj.getTitle());
            st.setString(2, obj.getCategory());
            st.setString(3, obj.getDescription());
            st.setBytes(4, obj.getFile());
            
            st.execute();
        } catch (SQLException e) {
            System.err.println("Error: "+e);
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void update(Document obj) {
       
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public Document findById(Integer id) {
        return null;
    }

    @Override
    public List<Document> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT id, title, category FROM files");
            
            rs =  st.executeQuery();
            List<Document> objList = new ArrayList<>();
            while(rs.next()) {
                Document obj = new Document(rs.getInt("id"), rs.getString("title"), rs.getString("category"));
                objList.add(obj);
            }
            return objList;
        } catch (SQLException e) {
            System.err.println("Error: "+e);
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
        return null;
    }
    
}

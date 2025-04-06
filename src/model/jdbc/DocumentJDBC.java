
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
            st = conn.prepareStatement("INSERT INTO files(title, category, description, file, numberPages, fileSize) VALUES (?, ?, ?, ?, ?, ?)");
            st.setString(1, obj.getTitle());
            st.setString(2, obj.getCategory());
            st.setString(3, obj.getDescription());
            st.setBytes(4, obj.getFile());
            st.setInt(5, obj.getNumberPages());
            st.setDouble(6, obj.getFileSize());
            
            st.execute();
        } catch (SQLException e) {
            System.err.println("Error JDBC: "+e);
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void update(Document obj) {
       
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("DELETE FROM files WHERE id = ?");
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            System.err.println("Error JDBC: "+e);
        } finally {
            Db.closeStatement(st);
        };
    }

    @Override
    public byte[] findByFileId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT file FROM files WHERE id = ?");
            st.setInt(1, id);
            
            rs =  st.executeQuery();
            return rs.getBytes("file");
        } catch (SQLException e) {
            System.err.println("Error JDBC: "+e);
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Document> findAllFileDate() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT id, title, category, description, numberPages, fileSize FROM files");
            
            rs =  st.executeQuery();
            List<Document> objList = new ArrayList<>();
            while(rs.next()) {
                Document obj = new Document(rs.getInt("id"), rs.getString("title"), rs.getString("category"),rs.getString("description"), rs.getInt("numberPages"), rs.getDouble("fileSize"));
                objList.add(obj);
            }
            return objList;
        } catch (SQLException e) {
            System.err.println("Error JDBC: "+e);
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
        return null;
    }
    
}

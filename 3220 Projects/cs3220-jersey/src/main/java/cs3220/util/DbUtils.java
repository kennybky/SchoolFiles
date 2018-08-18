package cs3220.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cs3220.model.File;

public class DbUtils {

    String url = "jdbc:mysql://localhost/cs3220stu45?useSSL=false";

    String username = "cs3220stu45";

    String password = "jl.*q!oW";

    Connection connection;

    public DbUtils()
    {
        try
        {
            connection = DriverManager.getConnection( url, username, password );
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            if( connection != null ) connection.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public File getFile( int id )
    {
        File file = null;

        try
        {
            String sql = "select * from files2 where id = ?";
            PreparedStatement pstmt = connection.prepareStatement( sql );
            pstmt.setInt( 1, id );
            ResultSet rs = pstmt.executeQuery();
            if( rs.next() )
            {
                file = new File();
                file.setId( rs.getInt( "id" ) );
                file.setName( rs.getString( "name" ) );
                file.setFolder( rs.getBoolean( "is_folder" ) );
                file.setParentId( rs.getInt( "parent_id" ) );
                if( rs.wasNull() ) file.setParentId( null );
            }

        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return file;
    }

    public File addFile( File file )
    {
        try
        {
            String sql = "insert into files2 (name, parent_id, is_folder) "
                + "values (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement( sql,
                Statement.RETURN_GENERATED_KEYS );
            pstmt.setString( 1, file.getName() );
            if( file.getParentId() != null )
                pstmt.setInt( 2, file.getParentId() );
            else
                pstmt.setNull( 2, Types.INTEGER );
            pstmt.setBoolean( 3, file.isFolder() );
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if( rs.next() ) file.setId( rs.getInt( 1 ) );
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return file;
    }

    public void updateFile( File file )
    {
        try
        {
            String sql = "update files2 set name = ?, parent_id = ?, "
                + "is_folder = ? where id = ?";
            PreparedStatement pstmt = connection.prepareStatement( sql );
            pstmt.setInt( 4, file.getId() );
            pstmt.setString( 1, file.getName() );
            if( file.getParentId() != null )
                pstmt.setInt( 2, file.getParentId() );
            else
                pstmt.setNull( 2, Types.INTEGER );
            pstmt.setBoolean( 3, file.isFolder() );
            pstmt.executeUpdate();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public void deleteFile( int id )
    {
        try
        {
            String sql = "delete from files2 where id = ?";
            PreparedStatement pstmt = connection.prepareStatement( sql );
            pstmt.setInt( 1, id );
            pstmt.executeUpdate();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public List<File> getFiles()
    {
        List<File> files = new ArrayList<File>();

        try
        {
            String sql = "select * from files2 where parent_id is null "
                + "order by is_folder desc, name asc";
            PreparedStatement pstmt = connection.prepareStatement( sql );
            ResultSet rs = pstmt.executeQuery();
            while( rs.next() )
            {
                File file = new File();
                file.setId( rs.getInt( "id" ) );
                file.setName( rs.getString( "name" ) );
                file.setFolder( rs.getBoolean( "is_folder" ) );
                files.add( file );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return files;
    }

    public List<File> getFiles( int parentId )
    {
        List<File> files = new ArrayList<File>();

        try
        {
            String sql = "select * from files2 where parent_id = ? "
                + "order by is_folder desc, name asc";
            PreparedStatement pstmt = connection.prepareStatement( sql );
            pstmt.setInt( 1, parentId );
            ResultSet rs = pstmt.executeQuery();
            while( rs.next() )
            {
                File file = new File();
                file.setId( rs.getInt( "id" ) );
                file.setName( rs.getString( "name" ) );
                file.setFolder( rs.getBoolean( "is_folder" ) );
                file.setParentId( rs.getInt( "parent_id" ) );
                files.add( file );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return files;
    }

}

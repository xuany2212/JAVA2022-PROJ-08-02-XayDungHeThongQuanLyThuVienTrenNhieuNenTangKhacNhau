package com.example.library;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;
    @SuppressLint("NewApi")

    public Connection connectionclass(){
        ip = "113.174.23.81";
        database = "QuanLyThuVien_8";
        uname = "sa";
        pass = "01012001";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + uname + ";password=" + pass +";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }
        return connection;
    }
}

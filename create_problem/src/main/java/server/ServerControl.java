/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileInfo;
import model.Message;
import model.Problem;

/**
 *
 * @author dodo
 */
public class ServerControl {

    InitDB initDB = new InitDB();
    private ServerSocket myServer;
    private Socket cliSocket;
    private int serverPort = 8888;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public ServerControl() {
        try {
            myServer = new ServerSocket(serverPort);
            while (true) {
                cliSocket = myServer.accept();
                oos = new ObjectOutputStream(cliSocket.getOutputStream());
                ois = new ObjectInputStream(cliSocket.getInputStream());
                receiveData();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void receiveData() {
        try {
            Message mesRei = (Message) ois.readObject();
            Problem p = mesRei.getP();
            
            initDB.insertData(p);
            FileInfo fi = mesRei.getFi();
            if(fi!=null){
                createFile(fi);
                System.out.println(fi);
            }
            else{
                System.out.println("Bugggg");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean createFile(FileInfo fileInfo) {
        BufferedOutputStream bos = null;

        try {
            if (fileInfo != null) {
                File fileReceive = new File(fileInfo.getDestinationDirectory()
                        + fileInfo.getFilename());
                bos = new BufferedOutputStream(
                        new FileOutputStream(fileReceive));
                // write file content
                bos.write(fileInfo.getDataBytes());
                bos.flush();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        new ServerControl();
    }
}

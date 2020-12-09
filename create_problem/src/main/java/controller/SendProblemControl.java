/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Problem;

/**
 *
 * @author dodo
 */
public class SendProblemControl {
    private Socket clientSocket;
    private int serverPort = 8888;
    private String serverHost = "localhost";
    ObjectOutputStream oos;
    public SendProblemControl() {
        try {
            clientSocket = new Socket(serverHost, serverPort);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendProblemControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendObject(Object p){
        try {
            oos.writeObject(p);
        } catch (IOException ex) {
            Logger.getLogger(SendProblemControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

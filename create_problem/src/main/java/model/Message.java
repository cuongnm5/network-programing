/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author dodo
 */
public class Message implements Serializable{
    Problem p;
    FileInfo fi;

    public Message(Problem p, FileInfo fi) {
        this.p = p;
        this.fi = fi;
    }

    public Problem getP() {
        return p;
    }

    public void setP(Problem p) {
        this.p = p;
    }

    public FileInfo getFi() {
        return fi;
    }

    public void setFi(FileInfo fi) {
        this.fi = fi;
    }
    
}

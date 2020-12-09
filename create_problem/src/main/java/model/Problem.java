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
public class Problem implements Serializable{
    private String name; 
    private String title;
    private String content;
    private String code;
    private String filePath;
    private String timeLimit;
    private String memLimit;

    public Problem(String name, String title, String content, String code, String filePath, String timeLimit, String memLimit) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.code = code;
        this.filePath = filePath;
        this.timeLimit = timeLimit;
        this.memLimit = memLimit;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Problem(String title, String content, String code, String filePath, String timeLimit, String memLimit) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.filePath = filePath;
        this.timeLimit = timeLimit;
        this.memLimit = memLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setMemLimit(String memLimit) {
        this.memLimit = memLimit;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public String getMemLimit() {
        return memLimit;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Problem{" + "title=" + title + ", content=" + content + ", code=" + code + ", filePath=" + filePath + '}';
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public Object[] toObjects(){
        return new Object[]{title,content,code,filePath};
    }
}

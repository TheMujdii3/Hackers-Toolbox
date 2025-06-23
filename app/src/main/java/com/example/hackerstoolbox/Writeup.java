package com.example.hackerstoolbox;

import java.io.Serializable;

public class Writeup  implements Serializable {
    public int id;
    public String title;
    public String tags;
    public String content;
    public String created_at;

    public int getId() { // or getWriteupId()
        return id;
    }
    public String getContent(){
        return content;
    }
}

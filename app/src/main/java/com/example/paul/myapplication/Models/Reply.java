package com.example.paul.myapplication.Models;

import java.io.Serializable;

/**
 * Created by Paul on 02-06-2017.
 */
public class Reply implements Serializable {
    String replytopic,submitter,image,time;

    public Reply(String replytopic,String submitter,String image,String time)
    {
        this.replytopic=replytopic;
        this.submitter=submitter;
        this.image=image;
        this.time=time;
    }

    public String getReplytopic()
    {
        return replytopic;
    }

    public String getSubmitter()
    {
        return submitter;
    }

    public String getImage()
    {
        return image;
    }

    public String getTime()
    {
        return time;
    }


}

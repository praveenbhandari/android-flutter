package com.example.imagedatabaseapplication;

public class Uploads {
private String mName;
private String mImageUrl;
public Uploads()
{

}

    public Uploads(String name, String imageUrl) {
        mName = name;
        mImageUrl = imageUrl;
    }
    public String getName()
    {
        return mName;
    }
    public void setName(String name)
    {
        mName=name;
    }
    public String getImageUrl()
    {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl)
    {
        mImageUrl=imageUrl;
    }



}

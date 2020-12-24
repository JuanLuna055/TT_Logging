package com.example.tt_logging;


public class Upload {
    private String mName;
    private String mImageUrl;

    public Upload()
    {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl)
    {
        if(name.trim().equals(""))
        {
            name="No Name";
        }
        mName=name;
        mImageUrl=imageUrl;
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


import com.google.firebase.database.Exclude;

public class Upload{
    private String mName;
    private String mImageUrl;
    //Para eliminar o descargar el archivo
    private String mKey;

    public Upload(){

    }

    public Upload(String name, String imageUrl){
        if(name.trim().equals("")){
            name = "No Name"+System.currentTimeMillis();
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName(){return mName;}
    public void setName(String name){mName = name; }
    public String getImageUrl(){return mImageUrl;}
    public void setImageUrl(String imageUrl){mImageUrl = imageUrl;}

    //metodos para eliminar o descargar la imagen
    @Exclude
    public String getKey(){return mKey;}
    @Exclude
    public void setKey(String key){mKey = key;}

}

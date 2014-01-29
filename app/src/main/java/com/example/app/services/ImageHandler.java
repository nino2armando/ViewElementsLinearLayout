package com.example.app.services;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by Nino on 29/01/14.
 */
public class ImageHandler
{    
    public static Bitmap extractImage(Intent data)
    {
        Bundle extra = data.getExtras();
        Bitmap image = (Bitmap)extra.get("data");
        return image;
    }
}

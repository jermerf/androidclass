package com.example.a8sqlitelist.model;

import java.util.Arrays;
import java.util.List;




public enum IconResource {
    CAMERA, PHONE, WRENCH, GALLERY;

    public static IconResource fromString(String iconResource) {
        try {
            return valueOf(iconResource);
        }catch (Exception ex){
            return CAMERA;
        }
    }

    public int getResource() {
        switch(this){
            case CAMERA: return android.R.drawable.ic_menu_camera;
            case PHONE:  return android.R.drawable.ic_menu_call;
            case WRENCH: return android.R.drawable.ic_menu_manage;
            case GALLERY: return android.R.drawable.ic_menu_gallery;
            default: return android.R.drawable.ic_menu_close_clear_cancel;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PHONE: return "Phone";
            case WRENCH: return "Wrench";
            case GALLERY: return  "Gallery";
            case CAMERA:
            default:
                return "Camera";
        }
    }
}

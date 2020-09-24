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

    public static IconResource iconFromPosition(int p) {
        switch (p){
            case 0: return IconResource.CAMERA;
            case 1: return IconResource.PHONE;
            case 2: return IconResource.WRENCH;
            case 3: return IconResource.GALLERY;
        }
        return IconResource.CAMERA;
    }
    public static List<String> getIconTitles() {
        return Arrays.asList("Camera", "Phone", "Wrench", "Gallery");
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

    public static int getResourceFromTitle(String title) {
        switch (title) {
            case "Camera": return IconResource.CAMERA.getResource();
            case "Phone": return IconResource.PHONE.getResource();
            case "Wrench": return IconResource.WRENCH.getResource();
            case "Gallery": return IconResource.GALLERY.getResource();
            default: return android.R.drawable.ic_menu_close_clear_cancel;
        }
    }
}

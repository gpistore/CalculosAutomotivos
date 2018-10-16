package common;

import android.content.Context;

public class comp_main_list_item {
    private Context context;
    int NameId;
    int ImageID;


    public comp_main_list_item(Context context, int  nameId, int ImageID){
        this.context = context;
        this.NameId= nameId;
        this.ImageID = ImageID;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getNameId() {
        return NameId;
    }

    public void setNameId(int nameId) {
        NameId = nameId;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}

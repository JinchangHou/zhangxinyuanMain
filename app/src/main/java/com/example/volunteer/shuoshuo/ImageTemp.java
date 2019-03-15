package com.example.volunteer.shuoshuo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 29208 on 2018/12/14.
 */
public class ImageTemp extends BmobObject {
    private BmobFile image;

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }
}

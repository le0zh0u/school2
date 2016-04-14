package com.school.dto.upstream;

import com.school.domain.MessageDO;

import java.util.List;

/**
 * Created by zhouchunjie on 16/4/15.
 */
public class MessageAddDto extends MessageDO {

    private List<String> imageList;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}

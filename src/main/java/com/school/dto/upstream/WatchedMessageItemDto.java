package com.school.dto.upstream;

import com.school.domain.MessageDO;

import java.util.List;

/**
 * Created by zhouchunjie on 16/4/4.
 */
public class WatchedMessageItemDto extends MessageDO {

    private String nickName;
    private String headPortrait;
    private Integer userId;
    private List<String> imageList;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

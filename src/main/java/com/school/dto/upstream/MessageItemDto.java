package com.school.dto.upstream;

import com.school.domain.MessageDO;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/31.
 */
public class MessageItemDto extends MessageDO {

    private List<String> imageList;
    private int likeCount = 0;
    private int commentCount = 0;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}

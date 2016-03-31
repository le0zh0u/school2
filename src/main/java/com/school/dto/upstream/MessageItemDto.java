package com.school.dto.upstream;

import com.school.domain.CommentDO;
import com.school.domain.MessageDO;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/31.
 */
public class MessageItemDto extends MessageDO {

    private List<String> imageList;
    private int likeCount = 0;
    private int commentCount = 0;
    private int watchCount = 0;
    private List<CommentDO> commentList;

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

    public List<CommentDO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDO> commentList) {
        this.commentList = commentList;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }
}

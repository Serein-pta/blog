package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentId;
    private String content;
    private Integer blogId;
    private Integer commenterId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

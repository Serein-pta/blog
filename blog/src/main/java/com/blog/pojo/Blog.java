package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Integer blogId;
    private String title;
    private String content;
    private String image;
    private String tag;
    private Integer isPrivate;//1.私密---2.公开
    private Integer authorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

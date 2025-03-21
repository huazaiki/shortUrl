package com.huazaiki.shorturl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 * @TableName short_url
 */
@TableName(value ="short_url")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrl {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String shortKey;

    /**
     * 
     */
    private String longUrl;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date expiresAt;
}
package com.huazaiki.shorturl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
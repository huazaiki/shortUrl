package com.huazaiki.shorturl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huazaiki.shorturl.entity.ShortUrl;

/**
* @author huazaiki
* @description 针对表【short_url】的数据库操作Service
* @createDate 2025-03-21 16:15:41
*/
public interface ShortUrlService extends IService<ShortUrl> {

    /**
     * 生成短链接
     * @param longUrl 长链接（原链接）
     * @return 短链接
     */
    public String shortenUrl(String longUrl);

    /**
     * 根据短链接获取真实长连接
     * @param shortKey 短链接
     * @return 长链接（原链接）
     */
    public String getLongUrl(String shortKey);

    public String hashShortUrl(String shortUrl);
}

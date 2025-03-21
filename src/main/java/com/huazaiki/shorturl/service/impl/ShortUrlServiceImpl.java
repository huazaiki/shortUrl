package com.huazaiki.shorturl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.f4b6a3.uuid.codec.base.Base62Codec;
import com.huazaiki.shorturl.entity.ShortUrl;
import com.huazaiki.shorturl.service.ShortUrlService;
import com.huazaiki.shorturl.mapper.ShortUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
* @author huazaiki
* @description 针对表【short_url】的数据库操作Service实现
* @createDate 2025-03-21 16:15:41
*/
@Service
public class ShortUrlServiceImpl extends ServiceImpl<ShortUrlMapper, ShortUrl>
    implements ShortUrlService{

    /**
     * 服务器地址
     */
    private final String serverUrl = "http://localhost:8080/shorturl";
    /**
     * 唯一ID
     */
    private final UUID uuid = UUID.randomUUID();


    @Autowired
    private ShortUrlMapper shortUrlMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String shortenUrl(String longUrl) {
        String shortKey = hashShortUrl(generateShortKey(longUrl));
        Date nowTime = new Date();
        ShortUrl shortUrl = ShortUrl.builder()
                        .longUrl(longUrl)
                        .shortKey(shortKey)
                        .createdAt(nowTime)
                        .expiresAt(new Date(nowTime.getTime() + 30L * 24 * 60 * 60 * 1000))
                        .build();
        shortUrlMapper.insert(shortUrl);
        redisTemplate.opsForValue().set(shortKey, longUrl, Duration.ofDays(30)); // 缓存 30 天
        return serverUrl + '/' + shortKey;
    }

    public String getLongUrl(String shortKey) {
        String cachedUrl = redisTemplate.opsForValue().get(shortKey);
        if (cachedUrl != null) return cachedUrl;
        return shortUrlMapper
                .selectOne(new QueryWrapper<ShortUrl>()
                    .eq("short_key", shortKey))
                .getLongUrl();
    }

    /**
     * 加密长连接，获取关键内容
     * @param url
     * @return
     */
    private String generateShortKey(String url) {
         return Base62Codec.INSTANCE.encode(uuid);
    }

    @Override
    public String hashShortUrl(String shortUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(shortUrl.getBytes());
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest).substring(0, 6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





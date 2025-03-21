package com.huazaiki.shorturl.controller;


import com.huazaiki.shorturl.entity.UrlRequest;
import com.huazaiki.shorturl.service.ShortUrlService;
import com.huazaiki.shorturl.utils.HttpUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by huazaiki on 2025/03/21.
 */
@RestController
@RequestMapping("/api/url")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        String shortUrl = shortUrlService.shortenUrl(request.getLongUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirect(@PathVariable String shortKey, @Autowired HttpServletResponse response) throws IOException {
        String longUrl = shortUrlService.getLongUrl(shortKey);
        if (longUrl != null) {
            /**
             * 检查并修复 URL 是否以 "http://" 或 "https://" 开头
             * 如果没有，默认添加 "http://" 或根据目标服务器支持情况添加 "https://"
             */
            if (!longUrl.matches("^(http|https)://.*$")) {
                // 尝试判断目标服务器是否支持 https
                if (HttpUtils.supportsHttps(longUrl)) {
                    // 如果支持 https，默认使用 https
                    longUrl = "https://" + longUrl;
                } else {
                    // 如果不支持 https，使用 http
                    longUrl = "http://" + longUrl;
                }
            }

            response.sendRedirect(longUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return null;
    }
}

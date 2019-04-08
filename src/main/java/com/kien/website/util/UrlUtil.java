package com.kien.website.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class UrlUtil {

    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }

        pathSegment = UriUtils.encodePathSegment(pathSegment,enc);
        return pathSegment;
    }

    public static String handleTitle(String title) {
        String temp = Normalizer.normalize(title,Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp.replaceAll("đ|Đ","d"))
                .replaceAll("")
                .toLowerCase()
                .trim()
                .replaceAll("[^\\w]+","-");
    }
}

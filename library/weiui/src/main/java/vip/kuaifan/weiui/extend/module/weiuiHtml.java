package vip.kuaifan.weiui.extend.module;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class weiuiHtml {

    /**
     * （无用、保留只是为了示例）将js中的src进行二次包装
     * @param content       内容
     * @param websiteUrl    网站网址
     * @return
     */
    @Deprecated
    public static String repairJsImage(String content, String websiteUrl) {
        String regexBody = "_c\\('image',\\s*\\{([^\\)]*)\\}";
        Pattern patternBody = Pattern.compile(regexBody, Pattern.CASE_INSENSITIVE);
        Matcher matcherBody = patternBody.matcher(content);
        //
        String result = content;
        while (matcherBody.find()) {
            String regexSrc = "(\"src\":\\s*\\\"([^\\)]*)\\\")";
            Pattern patternSrc = Pattern.compile(regexSrc, Pattern.CASE_INSENSITIVE);
            Matcher matcherSrc = patternSrc.matcher(matcherBody.group(1));
            //
            while (matcherSrc.find()) {
                String src = weiuiHtml.repairUrl(matcherSrc.group(2), websiteUrl);
                result = result.replaceAll(matcherSrc.group(1), "\"src\": \"" + src + "\"");
            }
        }
        return result;
    }

    /**
     * （无用、保留只是为了示例）将image标签中的src进行二次包装
     * @param content       内容
     * @param websiteUrl    网站网址
     * @return
     */
    @Deprecated
    public static String repairImage(String content, String websiteUrl) {
        String patternStr = "<image\\s*([^>]*)\\s*src=((\\\"|\\')(.*?)(\\\"|\\'))\\s*([^>]*)>";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String result = content;
        while (matcher.find()) {
            try {
                String src = matcher.group(3) + weiuiHtml.repairUrl(matcher.group(4), websiteUrl) + matcher.group(5);
                result = result.replaceAll(matcher.group(2), src);
            }catch (Exception ignored) { }
        }
        return result;
    }

    /**
     * 补全地址
     * @param url           图片地址
     * @param websiteUrl    网站网址
     * @return
     */
    public static String repairUrl(String url, String websiteUrl) {
        if (url == null || url.startsWith("http") || url.startsWith("ftp://")) {
            return url;
        }
        try {
            URL tmp = new URL(websiteUrl);
            String newUrl = tmp.getProtocol() + "://" + tmp.getHost();
            newUrl+= (tmp.getPort() > -1 && tmp.getPort() != 80) ? (":" + tmp.getPort()) : "";
            if (url.startsWith("/")) {
                return newUrl + url;
            }else{
                String path = "/";
                int lastIndex = tmp.getPath().lastIndexOf("/");
                if (lastIndex > -1){
                    path = tmp.getPath().substring(0, lastIndex + 1);
                }
                return newUrl + path + url;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}

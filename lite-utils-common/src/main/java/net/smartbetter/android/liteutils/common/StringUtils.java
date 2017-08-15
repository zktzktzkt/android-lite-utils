package net.smartbetter.android.liteutils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private StringUtils() {
    }
    public static StringUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final StringUtils instance = new StringUtils();
    }

    private static final String TELREGEX = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
    private static final String EMAILREGEX = "\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$";
    private static final String URLREGEX = "^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$";
    private static final String IPREGEX = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))";
    private static final String CHINESEREGEX = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
    private static final String IDNUMREGEX = "^\\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$";

    /**
     * Unicode转中文
     *
     * @param utfString
     * @return
     */
    public String convertUnicodeToChina(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }
        return sb.toString();
    }

    /**
     * 手机号码验证
     *
     * @param mobiles
     * @return
     */
    public boolean isMobile(String mobiles) {
        return check(mobiles, TELREGEX);
    }

    /**
     * 邮箱验证
     *
     * @param email
     * @return
     */
    public boolean isEmail(String email) {
        return check(email, EMAILREGEX);
    }

    /**
     * http、https Url验证
     *
     * @param url
     * @return
     */
    public boolean isHttpUrl(String url) {
        return check(url, URLREGEX);
    }

    /**
     * ip 验证
     *
     * @param ip
     * @return
     */
    public boolean isIp(String ip) {
        return check(ip, IPREGEX);
    }


    /**
     * 中文验证
     *
     * @param sequence
     * @return
     */
    public boolean isContainChinese(String sequence) {
        return check(sequence, CHINESEREGEX);
    }

    /**
     * 身份证号验证
     *
     * @param id
     * @return
     */
    public boolean isIDNumber(String id) {
        return check(id, IDNUMREGEX);
    }

    private boolean check(String content, String rule) {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(content);
        return m.matches();
    }

    /**
     * 十六进制字符串转换为byte数组
     *
     * @param hexString
     * @return
     */
    public byte[] hexString2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (char2Byte(hexChars[pos]) << 4 | char2Byte(hexChars[pos + 1]));
        }
        return d;
    }

    public byte char2Byte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * byte数组转换为十六进制字符串
     *
     * @param b
     * @return
     */
    public String bytes2HexString(byte[] b) {
        if (b.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < b.length; i++) {
            int value = b[i] & 0xFF;
            String hv = Integer.toHexString(value);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * int转换为byte数组
     *
     * @param res
     * @return
     */
    public byte[] int2Byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * byte数组转换为int
     *
     * @param res
     * @return
     */
    public int byte2Int(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[3] & 0xff) | ((res[2] << 8) & 0xff00) | ((res[1] << 16) & 0xff0000) | ((res[0] << 24) & 0xff000000);
        return targets;
    }

    /**
     * 保留几位小数
     */
    public String saveDecimals(int cnt, double value) {
        if (cnt == 2)
            return String.format("%.02f", value);
        else if (cnt == 1)
            return String.format("%.01f", value);
        else
            return String.format("%.0f", value);
    }

}
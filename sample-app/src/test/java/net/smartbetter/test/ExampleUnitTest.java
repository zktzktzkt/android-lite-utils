package net.smartbetter.test;

import net.smartbetter.android.liteutils.encrypt.MD5Utils;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void MD5WithBuilderTest() throws Exception {
        System.out.println(MD5Utils.getInstance().encryptMD5WithBuilder("123"));
    }
    @Test
    public void MD5WithBufferTest() throws Exception {
        System.out.println(MD5Utils.getInstance().encryptMD5WithBuffer("123"));
    }
}
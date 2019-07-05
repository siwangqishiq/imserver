package com.xinlan.clouds;

import com.UpYun;
import com.upyun.FormUploader;
import com.upyun.UpException;
import com.xinlan.Config;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CloudStorageTest {
    @Test
    public void testUpload() throws IOException, UpException {
        UpYun upyun = new UpYun(Config.CLOUD_STORAGE_PICPATH,
                Config.CLOUD_STORAGE_USER, Config.CLOUD_STORAGE_PWD);
        upyun.setDebug(true);
        upyun.setApiDomain(UpYun.ED_AUTO);

        File file = new File("D:\\BaiduNetdiskDownload\\新建文件夹\\003.jpg");
        upyun.setContentMD5(UpYun.md5(file));
        upyun.setTimeout(120);
        //upyun.setFileSecret("bac");
//        // 上传文件，并自动创建父级目录（最多10级）
        boolean result = upyun.writeFile("/"+System.currentTimeMillis()+"_sample.jpeg", file, true);
        Assert.assertTrue(result);
        // 图片宽度
        System.out.println("图片宽度:" + upyun.getPicWidth());
        // 图片高度
        System.out.println("图片高度:" + upyun.getPicHeight());
        // 图片帧数
        System.out.println("图片帧数:" + upyun.getPicFrames());
        // 图片类型
        System.out.println("图片类型:" + upyun.getPicType());
    }
}

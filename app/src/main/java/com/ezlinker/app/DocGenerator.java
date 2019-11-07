package com.ezlinker.app;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.SourceCodePath;

/**
 * @program: runlinker
 * @description: 文档生成器
 * @author: wangwenhai
 * @create: 2019-10-24 08:40
 **/

public class DocGenerator {
    /**
     * 包括设置请求头，缺失注释的字段批量在文档生成期使用定义好的注释
     */
    public static void main(String[] args) {
        testBuilderControllersApi();
    }

    private static void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:8080");
        config.setMd5EncryptedHtmlName(false);
        config.setStrict(true);
        config.setOutPath("apiDoc");
        config.setSourceCodePaths(
                SourceCodePath.path().setDesc("ezlinker-doc").setPath("ezlinker\\src\\main\\java\\com\\ezlinker\\app")
        );
        long start = System.currentTimeMillis();
        HtmlApiDocBuilder.builderControllersApi(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}

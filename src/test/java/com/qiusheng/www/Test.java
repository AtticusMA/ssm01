package com.qiusheng.www;

import org.springframework.beans.factory.annotation.Value;

public class Test {
    @Value("${adminPath}")
    protected static String adminPath;
}

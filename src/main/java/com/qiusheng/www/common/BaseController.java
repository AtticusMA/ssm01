package com.qiusheng.www.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public abstract class BaseController {

    //protected Logger logger = LoggerFactory.getLogger(BaseController.class);
    //protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${adminPath}")
    protected String adminPath;

}

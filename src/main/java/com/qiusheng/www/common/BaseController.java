package com.qiusheng.www.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;


@Controller
public abstract class BaseController {

    //protected Logger logger = LoggerFactory.getLogger(BaseController.class);
    //protected Logger logger = LoggerFactory.getLogger(getClass());

    //@Value("${adminPath}")
    protected String adminPath;

}

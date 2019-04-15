package com.qiusheng.www.security;

import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapping extends MappingSqlQuery<Resource> {

    public ResourceMapping(DataSource dataSource,String resourceQuery ){
        super(dataSource,resourceQuery);
        compile();
    }
    @Override
    protected Resource mapRow(ResultSet resultSet, int i) throws SQLException {
        String url = resultSet.getString(1);
        String role = resultSet.getString(2);
        Resource resource = new Resource(url,role);
        return resource;
    }
}

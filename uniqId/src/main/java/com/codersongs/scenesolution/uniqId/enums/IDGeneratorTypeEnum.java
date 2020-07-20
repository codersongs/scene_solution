package com.codersongs.scenesolution.uniqId.enums;

public enum IDGeneratorTypeEnum {
    UUID("uuid", "使用UUID生成分布式唯一ID"),
    MYSQL_INC("mysql_inc", "使用MySQL的自增生成uuid"),
    MYSQL_MULTI_INC("mysql_multi_inc", "使用MySQL的多个数据源自增生成uuid"),
    REDIS_INC("redis_inc", "Redis自增"),
    SNOW_FLAKE("redis_inc", "雪花算法");

    private String type;
    private String desc;

    IDGeneratorTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

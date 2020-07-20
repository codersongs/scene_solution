package com.codersongs.scenesolution.uniqId.enums;

/**
 * 数据源枚举
 */
public enum DataSourceEnum {
    DEFAULT("dataSource", ""),
    MULTISEQ1("multiDataSource1", ""),
    MULTISEQ2("multiDataSource2", ""),
    MULTIRANDOM("multiRandom", "");
    private String name;
    private String desc;

    DataSourceEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

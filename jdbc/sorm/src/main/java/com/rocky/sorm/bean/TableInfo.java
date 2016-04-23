package com.rocky.sorm.bean;

import java.util.List;
import java.util.Map;

public class TableInfo {

    /**
     * 表名
     */
    private String tname;

    /**
     * 所有字段的信息
     */
    private Map<String, ColumnInfo> columns;

    /**
     * 唯一主键（目前只处理表中有且只有一个主键的情况）
     */
    private ColumnInfo onlyPriKey;

    /**
     * 如果是联合主键则存储在这里
     */
    private List<ColumnInfo> priKeys;

    public TableInfo() {
    }

    public TableInfo(String tname, List<ColumnInfo> priKeys, Map<String, ColumnInfo> columns) {
        this.tname = tname;
        this.priKeys = priKeys;
        this.columns = columns;
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
        this.tname = tname;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }
}

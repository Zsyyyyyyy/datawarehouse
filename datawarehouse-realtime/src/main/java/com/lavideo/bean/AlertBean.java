package com.lavideo.bean;

import java.io.Serializable;

public class AlertBean implements Serializable {
    private String useId;
    private String type;
    private Long ts;

    public AlertBean(String useId, String type, Long ts) {
        this.useId = useId;
        this.type = type;
        this.ts = ts;
    }

    public AlertBean() {
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "AlertBean{" +
                "useId='" + useId + '\'' +
                ", type='" + type + '\'' +
                ", ts=" + ts +
                '}';
    }
}

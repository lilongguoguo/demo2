package com.common.district.report.model;

public class ArchiveContract {
    private Long id;

    private Long reportId;

    private Long contId;

    private Integer type;

    private Integer qualified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQualified() {
        return qualified;
    }

    public void setQualified(Integer qualified) {
        this.qualified = qualified;
    }
}
package iis.iis.entity;

import java.time.LocalDate;

public class ReportRequest {
    private String reportType;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors, getters, and setters

    public ReportRequest() {
    }

    public ReportRequest(String reportType, LocalDate startDate, LocalDate endDate) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

package com.example.aparttimejobv1;

public class JobInfo {
    private String companyName;
    private String LocationComapny;
    private String jobTitleReqiure;
    private String jobDescription;
    public JobInfo(){

    }
    public JobInfo(String companyName, String locationComapny, String jobTitleReqiure, String jobDescription) {
        this.companyName = companyName;
        LocationComapny = locationComapny;
        this.jobTitleReqiure = jobTitleReqiure;
        this.jobDescription = jobDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocationComapny() {
        return LocationComapny;
    }

    public void setLocationComapny(String locationComapny) {
        LocationComapny = locationComapny;
    }

    public String getJobTitleReqiure() {
        return jobTitleReqiure;
    }

    public void setJobTitleReqiure(String jobTitleReqiure) {
        this.jobTitleReqiure = jobTitleReqiure;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "companyName='" + companyName + '\'' +
                ", LocationComapny='" + LocationComapny + '\'' +
                ", jobTitleReqiure='" + jobTitleReqiure + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                '}';
    }
}

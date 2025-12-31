package com.Queensburry.hospital.dtos.common;

public class LabTestDto {
    private String labTestId;

    public String getLabTestId() {
        return labTestId;
    }

    public LabTestDto(String labTestId) {
        this.labTestId = labTestId;
    }

    public LabTestDto() {
    }

    public void setLabTestId(String labTestId) {
        this.labTestId = labTestId;
    }
}

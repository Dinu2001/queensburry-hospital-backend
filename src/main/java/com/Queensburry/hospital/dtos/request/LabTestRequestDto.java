package com.Queensburry.hospital.dtos.request;



public class LabTestRequestDto {
    private String labId;
    private String testName;
    private String description;
    private Double test_amount;
    private String preparationInstructions;

    public LabTestRequestDto() {
    }

    public LabTestRequestDto(String labId, String testName, String description, Double test_amount, String preparationInstructions) {
        this.labId = labId;
        this.testName = testName;
        this.description = description;
        this.test_amount = test_amount;
        this.preparationInstructions = preparationInstructions;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTest_amount() {
        return test_amount;
    }

    public void setTest_amount(Double test_amount) {
        this.test_amount = test_amount;
    }

    public String getPreparationInstructions() {
        return preparationInstructions;
    }

    public void setPreparationInstructions(String preparationInstructions) {
        this.preparationInstructions = preparationInstructions;
    }
}

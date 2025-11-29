package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.LabTestRequestDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.entity.LabTest;
import com.Queensburry.hospital.repo.LabTestRepo;
import com.Queensburry.hospital.utils.CommonFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabTestService {
    @Autowired
    private LabTestRepo labTestRepo;




    public String saveLabTest(LabTestRequestDto labTestRequestDto) {
        try{
            String id;
            do {
                CommonFile file = new CommonFile();
                id = "LT" + file.generatorRandomNumber();
            } while (labTestRepo.existsById(id));

            LabTest labTest = new LabTest(
                    id,
                    labTestRequestDto.getTestName(),
                    labTestRequestDto.getDescription(),
                    labTestRequestDto.getTest_amount(),
                    labTestRequestDto.getPreparationInstructions()
            );

            labTestRepo.save(labTest);

            return "Lab Test Saved Successfully with ID: " + id;
        }catch (Exception exception){
            exception.getMessage();
            System.out.println(exception);
        }
        return null;
    }

    public List<LabTestResponseDto> getAllLabTest() {
        try{
            List<LabTestResponseDto> labTestResponseDtos = new ArrayList<>();
            List<LabTest> labTests = labTestRepo.findAll();

            for(LabTest labTest:labTests){
                LabTestResponseDto labTestResponseDto = new LabTestResponseDto(
                        labTest.getLabId(),
                        labTest.getTestName(),labTest.getDescription(),
                        labTest.getTest_amount(),labTest.getPreparationInstructions()
                );
                labTestResponseDtos.add(labTestResponseDto);

            }

            return labTestResponseDtos;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }


    public String deleteById(String id) {
        try{
            Optional<LabTest> selected = labTestRepo.findById(id);
            if(selected.isEmpty()){
                return null;
            }else{
                labTestRepo.deleteById(id);
                return "deleted test" + id;
            }
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    public String updateLabTest(LabTestRequestDto labTestRequestDto, String id) {
        try{
            Optional<LabTest> selected = labTestRepo.findById(id);
            if(selected.isEmpty()){
                return null;
            }else{

                LabTest labTest = new LabTest(
                        id,
                        labTestRequestDto.getTestName(),
                        labTestRequestDto.getDescription(),
                        labTestRequestDto.getTest_amount(),
                        labTestRequestDto.getPreparationInstructions()
                );
                labTestRepo.save(labTest);
                return "updated test " + id;

            }
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}

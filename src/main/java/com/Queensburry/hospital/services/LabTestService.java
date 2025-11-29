package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.LabTestRequestDto;
import com.Queensburry.hospital.entity.LabTest;
import com.Queensburry.hospital.repo.LabTestRepo;
import com.Queensburry.hospital.utils.CommonFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

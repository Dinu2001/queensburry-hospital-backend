package com.Queensburry.hospital.utils;

import java.util.Random;

public class CommonFile {

    public int generatorRandomNumber(){
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }


}

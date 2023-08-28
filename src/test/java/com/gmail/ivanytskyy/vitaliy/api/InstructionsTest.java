package com.gmail.ivanytskyy.vitaliy.api;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.*;
import com.gmail.ivanytskyy.vitaliy.api.controllers.CarsController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.InstructionsController;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 28/08/2023
 */
public class InstructionsTest extends BaseTest{
    @Test(description = "Get all the instructions. Positive test", priority = 10)
    public void getAllInstructionsTest(){
        Map<Integer, List<Integer>> idMap = new LinkedHashMap<>();
        CarsController carsController = new CarsController(cookie);
        List<Integer> brandIds = carsController.getCarBrands().getData().stream().map(CarBrandData::getId).toList();
        for (Integer brandId : brandIds){
            List<Integer> modelIds = carsController
                    .getCarModels()
                    .getData()
                    .stream()
                    .filter(model -> model.getCarBrandId().equals(brandId))
                    .map(CarModelData::getId)
                    .toList();
            idMap.put(brandId, modelIds);
        }
        InstructionsController instructionsController = new InstructionsController(cookie);
        for (Map.Entry<Integer, List<Integer>> entry : idMap.entrySet()) {
            int page = 1;
            int brandId = entry.getKey();
            for (int modelId : entry.getValue()) {
                InstructionsResponse instructionsResponse = instructionsController
                        .getAllInstructions(brandId, modelId, page);
                Assert.assertEquals(instructionsResponse.getStatus(), "ok", "Status isn't ok");
                Assert.assertEquals(instructionsResponse.getCurrentPage(), page, "Page number is wrong");
                Assert.assertNotNull(instructionsResponse.getTotalItems(), "Number of items is null");
                List<InstructionData> instructions = instructionsResponse.getData();
                for (InstructionData instruction : instructions) {
                    Assert.assertNotNull(instruction.getId(), "Instruction id is null");
                    Assert.assertEquals(instruction.getCarBrandId(), brandId, "Brand id is wrong");
                    Assert.assertEquals(instruction.getCarModelId(), modelId, "Model id is wrong");
                    Assert.assertTrue(instruction.getFilename().endsWith(".pdf"), "File format is wrong");
                    Assert.assertFalse(instruction.getDescription() == null
                            || instruction.getDescription().isBlank(), "Description is null or blank");
                }
            }
        }
    }
    @Test(description = "Get instruction by id. Positive case.", priority = 20)
    public void getInstructionByIdTest(){
        CarsController carsController = new CarsController(cookie);
        int expectedInstructionId = 1;
        int expectedBrandId = 1;
        int expectedModelId = 1;
        String expectedBrandName = carsController.getCarBrandById(expectedBrandId).getData().getTitle();
        String expectedModelName = carsController.getCarModelById(expectedModelId).getData().getTitle();
        InstructionsController instructionsController = new InstructionsController(cookie);
        InstructionResponse instructionResponse = instructionsController.getInstructionById(expectedInstructionId);
        Assert.assertEquals(instructionResponse.getStatus(), "ok", "Status isn't ok");
        InstructionData instruction = instructionResponse.getData();
        Assert.assertEquals(instruction.getId(), expectedInstructionId, "Instruction id is wrong");
        Assert.assertEquals(instruction.getCarBrandId(), expectedBrandId, "Brand id is wrong");
        Assert.assertEquals(instruction.getCarModelId(), expectedModelId, "Model id is wrong");
        Assert.assertTrue(instruction.getFilename().endsWith(".pdf"), "File format is wrong");
        Assert.assertTrue(instruction.getDescription().contains(expectedBrandName), "Brand name is wrong");
        Assert.assertTrue(instruction.getDescription().contains(expectedModelName), "Model name is wrong");
    }
}
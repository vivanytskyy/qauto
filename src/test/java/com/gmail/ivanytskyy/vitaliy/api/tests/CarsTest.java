package com.gmail.ivanytskyy.vitaliy.api.tests;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.cars.CarRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.cars.*;
import com.gmail.ivanytskyy.vitaliy.api.controllers.CarsController;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 19/08/2023
 */
public class CarsTest extends BaseTest{

    @Test(description = "Get list of car brands. Positive case", priority = 10)
    public void getCarBrandsTest(){
        CarsController controller = new CarsController(cookie);
        CarBrandsResponse brandsResponse = controller.getCarBrands();
        Assert.assertEquals(brandsResponse.getStatus(), "ok", "Status isn't ok");

        List<CarBrandData> brands = brandsResponse.getData();
        Assert.assertTrue(brands.size() > 0, "List of brands is empty");
        for (CarBrandData brand : brands){
            Assert.assertNotNull(brand.getId(), "Brand id is null");
            Assert.assertNotNull(brand.getTitle(), "Brand title is null");
            Assert.assertNotNull(brand.getLogoFilename(), "Brand LogoFilename is null");
        }
    }
    @Test(description = "Get car brand by brand id. Positive case", priority = 20)
    public void getCarBrandByIdTest(){
        CarsController controller = new CarsController(cookie);
        List<CarBrandData> brands = controller.getCarBrands().getData();
        Assert.assertTrue(brands.size() > 0, "List of brands is empty");
        for (CarBrandData brandData : brands){
            CarBrandResponse brandResponse = controller.getCarBrandById(brandData.getId());
            Assert.assertEquals(brandResponse.getStatus(), "ok", "Status isn't ok");
            Assert.assertEquals(brandResponse.getData(), brandData, "Brands aren't equal");
        }
    }
    @Test(description = "Get list of car models. Positive case", priority = 30)
    public void getCarModelsTest(){
        CarsController controller = new CarsController(cookie);
        CarModelsResponse modelsResponse = controller.getCarModels();
        Assert.assertEquals(modelsResponse.getStatus(), "ok", "Status isn't ok");

        List<CarModelData> models = modelsResponse.getData();
        Assert.assertTrue(models.size() > 0, "List of models is empty");
        for (CarModelData model : models){
            Assert.assertNotNull(model.getId(), "Model id is null");
            Assert.assertNotNull(model.getTitle(), "Model title is null");
            Assert.assertNotNull(model.getCarBrandId(), "Brand id is null");
        }
    }
    @Test(description = "Get car model by model id. Positive case", priority = 40)
    public void getCarModelByIdTest(){
        CarsController controller = new CarsController(cookie);
        List<CarModelData> models = controller.getCarModels().getData();
        Assert.assertTrue(models.size() > 0, "List of models is empty");
        for (CarModelData model : models){
            CarModelResponse modelResponse = controller.getCarModelById(model.getId());
            Assert.assertEquals(modelResponse.getStatus(), "ok", "Status isn't ok");
            Assert.assertEquals(modelResponse.getData(), model, "Models aren't equal");
        }
    }
    @Test(description = "Create a new car. Positive case", priority = 50)
    public void createNewCarTest(){
        CarsController controller = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(1, 1000);
        int carBrandId = 1;
        int carModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(carBrandId)
                .carModelId(carModelId)
                .mileage(mileage)
                .build();

        CarResponse carResponse = controller.createNewCar(newCarRequest);
        Assert.assertEquals(carResponse.getStatus(), "ok", "Status isn't ok");
        CarData car = carResponse.getData();
        Assert.assertNotNull(car.getId(), "Car id is null");
        Assert.assertEquals(car.getCarBrandId(), carBrandId, "Car brand id is incorrect");
        Assert.assertEquals(car.getCarModelId(), carModelId, "Car model id is incorrect");
        Assert.assertNotNull(car.getBrand(), "Brand is null");
        Assert.assertNotNull(car.getModel(), "Model is null");
        Assert.assertNotNull(car.getLogo(), "Logo is null");
        Assert.assertEquals(car.getMileage(), mileage, "Mileage is incorrect");
        Assert.assertNotNull(car.getInitialMileage(), "Initial mileage is null");
        Assert.assertNotNull(car.getUpdatedMileageAt(), "Updated mileage is null");
    }
    @Test(description = "Get list of current user's cars. Positive case", priority = 60)
    public void getCurrentUserCarsTest(){
        CarsController controller = new CarsController(cookie);
        //Create cars for current user
        List<Integer> brandIds = controller.getCarBrands().getData().stream().map(CarBrandData::getId).toList();
        List<CarModelData> models = controller.getCarModels().getData();
        Map<Integer, List<Integer>> idMap = new LinkedHashMap<>();
        for (int brandId : brandIds){
            List<Integer> modelIdsOfBrand = models.stream()
                    .filter(model -> model.getCarBrandId().equals(brandId))
                    .map(CarModelData::getId)
                    .toList();
            idMap.put(brandId, modelIdsOfBrand);
        }
        List<CarData> newCars = new ArrayList<>();
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = idMap.entrySet().iterator();
        Random random = new Random();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            for (Integer modelId : entry.getValue()){
                int mileage = random.nextInt(1, 1000);
                CarRequest newCarRequest = CarRequest
                        .builder()
                        .carBrandId(entry.getKey())
                        .carModelId(modelId)
                        .mileage(mileage)
                        .build();
                CarData carData = controller.createNewCar(newCarRequest).getData();
                newCars.add(carData);
            }
        }

        CarsResponse carsResponse = controller.getCurrentUserCars();
        Assert.assertEquals(carsResponse.getStatus(), "ok", "Status isn't ok");
        List<CarData> cars = carsResponse.getData();
        Assert.assertTrue(cars.size() > 0, "List of models is empty");
        for (CarData car : cars){
            Assert.assertNotNull(car.getId(), "Car id is null");
            Assert.assertNotNull(car.getCarBrandId(), "Car brand id is null");
            Assert.assertNotNull(car.getCarModelId(), "Car model id is null");
            Assert.assertNotNull(car.getBrand(), "Brand is null");
            Assert.assertNotNull(car.getModel(), "Model is null");
            Assert.assertNotNull(car.getLogo(), "Logo is null");
            Assert.assertNotNull(car.getMileage(), "Mileage is null");
            Assert.assertNotNull(car.getInitialMileage(), "Initial mileage is null");
            Assert.assertNotNull(car.getUpdatedMileageAt(), "Updated mileage is null");
            Assert.assertTrue(newCars.contains(car), "Car wasn't found at list");
        }
    }
    @Test(description = "Get car by id. Positive case", priority = 70)
    public void getCarByIdTest(){
        CarsController controller = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(1, 1000);
        int carBrandId = 1;
        int carModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(carBrandId)
                .carModelId(carModelId)
                .mileage(mileage)
                .build();
        CarResponse expectedCarResponse = controller.createNewCar(newCarRequest);
        CarResponse resultCarResponse = controller.getCarById(expectedCarResponse.getData().getId());
        Assert.assertEquals(resultCarResponse, expectedCarResponse, "Car wasn't found");
    }
    @Test(description = "Edit car by id. Positive case", priority = 80)
    public void editCarByIdTest(){
        CarsController controller = new CarsController(cookie);

        //Create new car for current user
        Random random = new Random();
        int initialMileage = random.nextInt(1, 1000);
        int initialCarBrandId = 1;
        int initialCarModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(initialCarBrandId)
                .carModelId(initialCarModelId)
                .mileage(initialMileage)
                .build();
        CarResponse expectedCarResponse = controller.createNewCar(newCarRequest);
        int editedMileage = random.nextInt(initialMileage, 1000);
        int editedCarBrandId = 2;
        int editedCarModelId = 6;
        CarRequest editedCarRequest = CarRequest
                .builder()
                .carBrandId(editedCarBrandId)
                .carModelId(editedCarModelId)
                .mileage(editedMileage)
                .build();
        CarResponse editedCarResponse = controller.editCarById(editedCarRequest, expectedCarResponse.getData().getId());
        Assert.assertNotEquals(editedCarResponse, expectedCarResponse, "Updating failed");
        Assert.assertEquals(editedCarResponse.getData().getCarBrandId(), editedCarBrandId,
                "Car brand id wasn't updated");
        Assert.assertEquals(editedCarResponse.getData().getCarModelId(), editedCarModelId,
                "Car model id wasn't updated");
        Assert.assertEquals(editedCarResponse.getData().getMileage(), editedMileage,
                "Mileage wasn't updated");
    }
    @Test(description = "Delete a car by id. Positive case", priority = 90)
    public void deleteCarByIdTest(){
        CarsController controller = new CarsController(cookie);
        // Create a new car for current user
        Random random = new Random();
        int mileage = random.nextInt(1, 1000);
        int carBrandId = 1;
        int carModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(carBrandId)
                .carModelId(carModelId)
                .mileage(mileage)
                .build();
        CarResponse newCarResponse = controller.createNewCar(newCarRequest);
        // Delete a car
        DeleteCarResponse deleteCarResponse = controller.deleteCarById(newCarResponse.getData().getId());
        Assert.assertEquals(deleteCarResponse.getStatus(), "ok", "Status isn't ok");
        DeletedCarData deletedCarData = deleteCarResponse.getData();
        Assert.assertEquals(deletedCarData.getCarId(), newCarResponse.getData().getId(), "Car wasn't deleted");
    }
}
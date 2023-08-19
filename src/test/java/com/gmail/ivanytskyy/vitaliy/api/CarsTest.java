package com.gmail.ivanytskyy.vitaliy.api;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.*;
import com.gmail.ivanytskyy.vitaliy.api.controllers.CarsController;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

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

    @Test(description = "Get list of current user's cars. Positive case", priority = 50)
    public void getCurrentUserCarsTest(){
        CarsController controller = new CarsController(cookie);
        CarsResponse carsResponse = controller.getCurrentUserCars();
        Assert.assertEquals(carsResponse.getStatus(), "ok", "Status isn't ok");
        //todo create cars for current user
        /*List<CarData> cars = carsResponse.getData();
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
        }*/
    }
}
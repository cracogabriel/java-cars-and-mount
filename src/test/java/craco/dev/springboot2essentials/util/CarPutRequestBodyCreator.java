package craco.dev.springboot2essentials.util;

import craco.dev.springboot2essentials.requests.CarPutRequestBody;

public class CarPutRequestBodyCreator {

    public static CarPutRequestBody createCarPutRequestBodyToBeSaved() { // without id
        CarPutRequestBody newCarPutRequestBody = new CarPutRequestBody(1,"Eclipse", "verde", 2000);

        return newCarPutRequestBody;
    }

}

package craco.dev.springboot2essentials.util;

import craco.dev.springboot2essentials.requests.CarPostRequestBody;

public class CarPostRequestBodyCreator {

    public static CarPostRequestBody createCarPostRequestBodyToBeSaved() { // without id
        CarPostRequestBody newCarPostRequestBody = new CarPostRequestBody("Eclipse", "vermelho", 2000);

        return newCarPostRequestBody;
    }

}

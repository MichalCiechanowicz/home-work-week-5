package com.poweremabox.homeworkweek5.ui;

import com.poweremabox.homeworkweek5.service.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Route("weathers")
@Controller
public class WeatherView extends VerticalLayout {

    private final WeatherService weatherService;
    private final TextField textField;
    private final Button button;

    @Autowired
    public WeatherView(WeatherService weatherService) {
        this.weatherService = weatherService;
        textField = new TextField("City");
        button = new Button("Check Wheater");
        add(textField, button);
        displayWeatherInfo();
    }

    private void getCity() {
        weatherService.setCity(textField.getValue());
    }

    private void displayWeatherInfo() {
        VerticalLayout verticalLayout = new VerticalLayout();
        Dialog dialog = new Dialog();
        button.addClickListener(action -> {
            getCity();
            Label cityName = new Label("Weather for " + textField.getValue().toUpperCase());
            Label tempLabel = new Label("Temperature: " + weatherService.getTempInfo() + " C");
            Label cloudLabel = new Label("Cloud: " + weatherService.getCloudInfo());
            Label pressure = new Label("Atmospheric pressure: " + weatherService.getPressureInfo()+" hPa");
            Image image = new Image(weatherService.getWeatherPicture(), "DummyImage");
            verticalLayout.add(cityName, tempLabel, cloudLabel, pressure, image);
            dialog.add(verticalLayout);
            dialog.open();

        });
    }
}

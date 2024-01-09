package resources.testData;

import pojo.googleMaps.AddPlace;
import pojo.googleMaps.Location;

import java.util.ArrayList;
import java.util.List;

public class PayLoads {
    public AddPlace addPlacePayloadData()
    {
        AddPlace addPlace=new AddPlace();
        addPlace.setAccuracy(30);
        addPlace.setAddress("Mitte , 10210 Berlin");
        addPlace.setLanguage("English");
        addPlace.setName("Sara Abouelela");
        addPlace.setWebsite("www.sara.com");
        addPlace.setPhoneNumber("+20-9282666355");

        Location location= new Location();
        location.setLat(-38.383494);
        location.setLng(33.383494);
        addPlace.setLocation(location);

        List<String> types =new ArrayList<>();
        types.add("Edeka");
        types.add("Lidl");
        addPlace.setTypes(types);
        return addPlace;
    }

    public AddPlace addPlacePayloadData(String name, String adress, String language) {
        AddPlace addPlace=new AddPlace();
        addPlace.setAccuracy(30);
        addPlace.setAddress(adress);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setWebsite("www.sara.com");
        addPlace.setPhoneNumber("+20-9282666355");

        Location location= new Location();
        location.setLat(-38.383494);
        location.setLng(33.383494);
        addPlace.setLocation(location);

        List<String> types =new ArrayList<>();
        types.add("Edeka");
        types.add("Lidl");
        addPlace.setTypes(types);
        return addPlace;
    }

    public String  deletePlacePayloadData(String place_id)
    {
        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}

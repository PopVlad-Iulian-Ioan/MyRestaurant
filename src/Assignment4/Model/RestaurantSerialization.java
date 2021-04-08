package Assignment4.Model;

import java.io.*;

public class RestaurantSerialization {
    private String fileName;
    public RestaurantSerialization(String fileName){
        this.fileName=fileName;
    }
    public void serialize(Restaurant restaurant ) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(restaurant);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public  Restaurant DeSerialize() {
        Restaurant restaurant=null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            restaurant = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
            return restaurant;
        } catch (IOException i) {
            System.out.println(i);
            restaurant = new Restaurant();
            serialize(restaurant);
            return restaurant;
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return new Restaurant();
        }
    }
}

package Assignment4;

import Assignment4.Model.Restaurant;
import Assignment4.Model.RestaurantSerialization;
import Assignment4.View.AdminUI;
import Assignment4.View.ChefUI;
import Assignment4.View.MainUI;
import Assignment4.View.WaiterUI;

public class Main {

    public static void main(String[] args) {
	if(args.length!=1)
        System.out.println ("Wrong number of arguments");
	else {
        RestaurantSerialization restaurantSerialization= new RestaurantSerialization (args[0]);
        Restaurant restaurant =restaurantSerialization.DeSerialize ();
        AdminUI adminUI=new AdminUI ( restaurant,restaurantSerialization );
        WaiterUI waiterUI=new WaiterUI (restaurant,restaurantSerialization);
        new MainUI (waiterUI,adminUI,new ChefUI () );
    }
    }
}

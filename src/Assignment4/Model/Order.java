package Assignment4.Model;

import java.util.Date;
import java.util.Objects;

public class Order {
   public int orderID;
   public Date date;
   public int table;
   public Order(int orderID,Date date,int table){
       this.orderID=orderID;
       this.date=date;
       this.table=table;
   }
    public Order(int orderID,int table){
        this.orderID=orderID;
        this.date=new Date (  );
        this.table=table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Order order = (Order) o;
        return orderID == order.orderID &&
                table == order.table &&
                date.equals ( order.date );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( orderID , date , table );
    }
}

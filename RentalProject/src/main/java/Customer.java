import java.util.ArrayList;
import java.util.List;
public class Customer {
    private final String name, email, address,phone;
    private final int id;
    public List<Item> rentals=new ArrayList<>();
    Customer(String name, String email, String phone, String address, int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id=id;
        RentalStore.allCustomers.add(this);
    }
    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    String getEmail() {
        return this.email;
    }

    String getPhone() {
        return this.phone;
    }

    String getAddress() {
        return this.address;
    }
    void getRentals(){
        int count = 0;
            if (this.rentals.isEmpty()) {
                System.out.println("The List is Empty!");
            } else {
                for (int i=0;i<this.rentals.size();i++) {
                    //if (rentals.get(i) != null) {
                        count++;
                        System.out.println(count + " " + rentals.get(i).getId());
                    //}
                }
            }
    }

    public String getAllInformation() {
        return "Name : " + this.name + ", Phone Number : " + this.phone + ", Address : " + this.address + ", Email : " + this.email + " ,ID : " + this.id;
    }
}

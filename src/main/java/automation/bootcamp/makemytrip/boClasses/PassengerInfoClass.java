package automation.bootcamp.makemytrip.boClasses;

public class PassengerInfoClass {

    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    PassengerBookingInfoClass passengerBookingInfoClass;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PassengerBookingInfoClass getPassengerBookingInfoClass() {
        return passengerBookingInfoClass;
    }

    public void setPassengerBookingInfoClass(PassengerBookingInfoClass passengerBookingInfoClass) {
        this.passengerBookingInfoClass = passengerBookingInfoClass;
    }

    @Override
    public String toString() {
        return "PassengerInfoClass{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passengerBookingInfoClass=" + passengerBookingInfoClass +
                '}';
    }


}

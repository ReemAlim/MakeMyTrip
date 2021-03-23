package automation.bootcamp.makemytrip.boClasses;

/***
 * Unused class for now but keeping it for further implementation
 */
public class RoomRecommendDetailsClass {
    private String roomType;
    private int numberOfPersonsInRecommendedRoom;
    private double paymentForTheRoom;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfPersonsInRecommendedRoom() {
        return numberOfPersonsInRecommendedRoom;
    }

    public void setNumberOfPersonsInRecommendedRoom(int numberOfPersonsInRecommendedRoom) {
        this.numberOfPersonsInRecommendedRoom = numberOfPersonsInRecommendedRoom;
    }

    public double getPaymentForTheRoom() {
        return paymentForTheRoom;
    }

    public void setPaymentForTheRoom(double paymentForTheRoom) {
        this.paymentForTheRoom = paymentForTheRoom;
    }

    @Override
    public String toString() {
        return "RoomRecommendDetailsClass{" +
                "roomType='" + roomType + '\'' +
                ", numberOfPersonsInRecommendedRoom=" + numberOfPersonsInRecommendedRoom +
                ", paymentForTheRoom=" + paymentForTheRoom +
                '}';
    }


}

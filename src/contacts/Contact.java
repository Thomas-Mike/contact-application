package contacts;

public class Contact {
    private int phoneNumber;
    private String fullName;
//    private String lastFirst;

    Contact(String fullName, int phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullNameName(){
        return this.fullName;
    }


    public int getPhoneNumber(){
        return this.phoneNumber;
    }



    //Bonus for end edit contact
//    public void setFirstName(){
//
//    }
}

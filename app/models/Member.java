package models;

import controllers.Accounts;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {

    public String name;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double startingWeight;


    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentList = new ArrayList<Assessment>();

    public Member(String name, String email, String password, String address, String gender, double height, double startingWeight){

        setName(name);
        setEmail(email);
        setPassword(password);
        setAddress(address);
        setGender(gender);
        setHeight(height);
        setStartingWeight(startingWeight);
    }

    /**
     * Mutator methods to set the member fields
     *
     */
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setStartingWeight(double startingWeight){
        this.startingWeight = startingWeight;
    }

    public double getStartWeight() {

        return startingWeight;
    }

    public double getHeight(){

        return height;
    }

    public static Member findByEmail(String email)
    {

        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {

        return this.password.equals(password);
    }


    public static double calculateBMI(){

        double bmi;
        Member member = Accounts.getLoggedInMember();
        bmi = member.getStartWeight() * (703 / (member.getHeight() * member.getHeight()));

        return bmi;

    }

}

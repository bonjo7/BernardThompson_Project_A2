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
    public double latestBmi = 0.0;



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
        calculateBmi();
        determineBMICategory();

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

    public double calculateBmi(){

       if(assessmentList.size() == 0){
           latestBmi = (startingWeight / (height * height));
       }else{
           Assessment assessment = assessmentList.get(assessmentList.size() -1);
           latestBmi = (assessment.weight / (height * height));
       }

       return latestBmi;
    }

    public String determineBMICategory(){

        if(latestBmi < 16 ){
            return "SEVERELY UNDERWEIGHT";
        }
        else if(latestBmi >= 16 && latestBmi < 18.5){
            return "UNDERWEIGHT";
        }
        else if(latestBmi >= 18.5 && latestBmi < 25) {
            return "NORMAL";
        }
        else if(latestBmi >= 25 && latestBmi < 30) {
            return "OVERWEIGHT";
        }
        else if(latestBmi >= 30 && latestBmi < 35) {
            return "MODERATELY OBESE";
        }
        else if(latestBmi >= 35) {
            return "SEVERELY OBESE";
        }

        return "No value for BMI";
    }

}

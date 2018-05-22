package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Assessment extends Model {

    public LocalDateTime timePoint;
    public double weight;
    public double chest;
    public double thigh;
    public double arm;
    public double waist;
    public double hips;
    public String comment;

    public Assessment(double weight, double chest, double thigh, double arm, double waist, double hips, String comment){

        timePoint = LocalDateTime.now();
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.arm = arm;
        this.waist = waist;
        this.hips = hips;
        setComment(comment);

    }

    public void setComment(String comment){
        this.comment = comment;
    }

}

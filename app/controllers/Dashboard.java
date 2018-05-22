package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentList = member.assessmentList;
    render ("dashboard.html", member, assessmentList);
  }

  public static void addAssessment(double weight, double chest, double thigh, double arm, double waist, double hips, String comment)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, arm, waist, hips, comment);
    member.assessmentList.add(assessment);
    assessment.save();
    Logger.info("Adding Assessment" + weight + chest + thigh + arm + waist + hips);
    redirect("/dashboard");
  }

  public static void addComment(Long id, String comment){
    Assessment assessment = Assessment.findById(id);
    assessment.setComment(comment);
    assessment._save();
    render("assessment.html");
  }

  public static void deleteAssessment(Long id){
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(id);
    List<Assessment> assessmentList = member.assessmentList;
    Logger.info ("Removing" + assessment.hips + assessment.waist + assessment.arm + assessment.thigh + assessment.chest + assessment.weight);
    member.assessmentList.remove(assessment);
    member.save();
    assessment.delete();
    render("dashboard.html", member, assessmentList);
  }
}









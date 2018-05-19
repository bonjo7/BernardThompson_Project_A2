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
    //List<Member> members = Member.findAll();
    render ("dashboard.html", member, assessmentList);
  }

  public static void addAssessment(double weight, double chest, double thigh, double arm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, arm, waist, hips);
    member.assessmentList.add(assessment);
    assessment.save();
    Logger.info("Adding Assessment" + weight + chest + thigh + arm + waist + hips);
    redirect("/dashboard");
  }
}









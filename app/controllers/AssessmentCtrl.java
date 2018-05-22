package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class AssessmentCtrl extends Controller {

    public static void index(Long id)
    {
        Member member = Member.findById(id);
        Logger.info("Member id = " +id);
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = trainer.members;
        render("assessment.html", member, trainer, members);
    }
}

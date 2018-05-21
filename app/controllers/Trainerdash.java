package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Trainerdash extends Controller {

    public static void index() {
        Logger.info("Rendering Trainer Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = trainer.members;
        render("trainerdash.html", members);
    }

    public static void deleteMember (Long id){

        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Member> members = trainer.members;
        Logger.info("Deleting Member : " + member.name);
        trainer.members.remove(member);
        trainer.save();
        member.delete();
        render("trainerdash.html", members);
    }

}

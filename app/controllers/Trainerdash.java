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

        Logger.info("Deleting a Member");
        Member member = Member.findById(id);
        member.delete();
        redirect("/trainerdash");

    }
}

package com.piksenia.thymeleaf.pdfCreator.servicescontrollers;

import com.piksenia.thymeleaf.pdfCreator.models.Evaluation;
import com.piksenia.thymeleaf.pdfCreator.models.Profile;
import com.piksenia.thymeleaf.pdfCreator.models.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileCreationService {

    private Profile generateContent(String profileName){
        Profile profile = new Profile();

        if(profileName.equals("piksenia")){
            profile.setUsername("Piksenia");
            profile.setAge(27);
            profile.setMotto("The secret of success is to start");
            profile.setJob("Fullstack Java Developer");
            profile.setSkillList(List.of(
                    new Skill("Java", Evaluation.GOOD),
                    new Skill("TypeScript", Evaluation.GOOD),
                    new Skill("Postgresql", Evaluation.OK),
                    new Skill("C#", Evaluation.BAD),
                    new Skill("UML", Evaluation.EXCELLENT)));
        }else{
            profile.setUsername("Unknown");
            profile.setAge(0);
            profile.setMotto("Default");
            profile.setJob("Default Developer");
        }
        return profile;
    }

    protected List<Profile> initializeProfiles(List<String> profileNames){
        List<Profile> profileList = new ArrayList<>();
        for(String profile: profileNames){
            profileList.add(generateContent(profile));
        }
        return profileList;
    }

}

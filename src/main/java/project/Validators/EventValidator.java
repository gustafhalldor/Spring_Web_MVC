package project.Validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.persistence.entities.Event;

import java.util.Date;

/**
 * Created by geelo on 20-Nov-16.
 */
@Component("eventValidator")
public class EventValidator implements Validator {

    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Event.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Event eve = (Event) obj;
        if(eve.getAgeMin() < 18){
            errors.rejectValue("ageMin", "ageMin.required", "Minimum age cannot be below 18");
        }

        if(eve.getLat() == 0.0 && eve.getLgt() == 0.0) errors.rejectValue("lgt", "lgt.required", "Event must have a location!");

        // Validate dates when they are working. Check if fields are empty and also check if enddate is before startdate
        // + check if startdate is in the past

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Name cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required", "Description cannot be blank");
    }

}

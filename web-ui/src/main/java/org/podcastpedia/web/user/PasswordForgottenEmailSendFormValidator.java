package org.podcastpedia.web.user;

import org.podcastpedia.common.domain.User;
import org.podcastpedia.core.user.UserDao;
import org.podcastpedia.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Contact form validator
 *
 * @author ama
 *
 */
public class PasswordForgottenEmailSendFormValidator implements Validator{

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User)target;

        User userDetails = userDao.getUserByUsername(user.getUsername());
        if (userDetails == null){
			errors.rejectValue("username", "invalid.email.not_registered");
		} else {
            user.setDisplayName(userDetails.getDisplayName());
        }

	}

}

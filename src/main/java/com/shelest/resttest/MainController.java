package com.shelest.resttest;

import com.shelest.resttest.db.Gender;
import com.shelest.resttest.db.User;
import com.shelest.resttest.db.UserRepository;
import com.shelest.resttest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserModelAssembler assembler;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor birthDateCDE = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, birthDateCDE);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<EntityModel<User>> users = StreamSupport.stream(userRepository.findAll().spliterator(), false).map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(MainController.class).getAllUsers()).withSelfRel());
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public EntityModel<User> newUser(@RequestBody User user) {
        System.out.println(user.toString());
        return assembler.toModel(userRepository.save(user));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public EntityModel<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
        User editUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        editUser.setName(user.getName());
        editUser.setBirthday(user.getBirthday());
        editUser.setGender(user.getGender());
        return assembler.toModel(userRepository.save(editUser));
    }
}

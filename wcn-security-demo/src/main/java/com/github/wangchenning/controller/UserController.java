package com.github.wangchenning.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.wangchenning.dto.User;
import com.github.wangchenning.dto.UserQueryCondition;
import com.github.wangchenning.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Musk
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @JsonView(User.UserSimpleView.class)
    @PostMapping
    public User create(@Valid @RequestBody User user) {
//           , BindingResult errors
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> {
//                System.out.println(error.getDefaultMessage());
//            });
//        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @JsonView(User.UserSimpleView.class)
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + ":" + error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @JsonView(User.UserSimpleView.class)
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id) {
        System.out.println(id);
    }


    @JsonView(User.UserSimpleView.class)
    @GetMapping
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 1, size = 17, sort = "username.desc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")
    public User getInfo(@ApiParam("用户id") @PathVariable("id") String id) {
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("wcn");
        return user;
//        throw new UserNotExistException(id);
//        throw new RuntimeException("user is not exist");
    }


}

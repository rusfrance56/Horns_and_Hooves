package com.rest_jpa.facade;

import com.rest_jpa.entity.User;
import com.rest_jpa.entity.to.*;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.servise.CustomerOrderService;
import com.rest_jpa.servise.TaskService;
import com.rest_jpa.servise.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private TaskService taskService;
    private CustomerOrderService customerOrderService;

    @Override
    public UserResponseTO create(UserRequestTO to) {
        User user = new User();
        setUserParametersFromTO(user, to);
        Long newUserId = userService.register(user).getId();
        UserResponseTO newUser = new UserResponseTO(user);
        newUser.setId(newUserId);
        return newUser;
    }

    @Override
    public void update(UserRequestTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        User user = userService.findById(to.getId());
        setUserParametersFromTO(user, to);
        userService.update(user);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public List<UserResponseTO> findAll() {
        List<User> all = userService.findAll();
        return all.stream().map(UserResponseTO::new).collect(Collectors.toList());
    }

    @Override
    public Page<UserResponseTO> findAllWithPagination(int page, int size) {
        Page<User> usersPage = userService.findAllWithPagination(page, size);
        List<UserResponseTO> usersTO = usersPage.getContent().stream().map(UserResponseTO::new)
                .collect(Collectors.toList());
        return new PageImpl<>(usersTO, usersPage.getPageable(), usersPage.getTotalElements());
    }

    @Override
    public UserResponseTO findById(long id) {
        User user = userService.findById(id);
        return new UserResponseTO(user);
    }

    @Override
    public List<UserResponseTO> findByDepartment(String department) {
        Department dep = Department.valueOf(department);
        List<User> usersByDep = userService.findByDepartment(dep);
        return usersByDep.stream().map(UserResponseTO::new).collect(Collectors.toList());
    }

    @Override
    public AuthUserTO getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return new AuthUserTO(currentUser);
    }

    private void setUserParametersFromTO(User user, UserRequestTO to) {
        user.setUserName(to.getUserName());
        if (user.getId() == null) {
            user.setPassword(to.getPassword());
        }
        user.setName(to.getName());
        user.setSurname(to.getSurname());
        user.setDepartment(to.getDepartment() != null ? to.getDepartment() : null);
        user.setAddress(to.getAddress());
        user.setEmail(to.getEmail());
        user.setPhone(to.getPhone());
        if (!to.getTasks().isEmpty()) {
            user.setTasks(taskService.findAllByIds(to.getTasks().stream().map(TaskTO::getId).collect(Collectors.toList())));
        }
        if (!to.getOrders().isEmpty()) {
            user.setOrders(customerOrderService.findAllByIds(to.getOrders().stream().map(CustomerOrderResponseTO::getId).collect(Collectors.toList())));
        }
    }
}

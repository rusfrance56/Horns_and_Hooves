package com.rest_jpa.facade;

import com.rest_jpa.entity.User;
import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.entity.to.UserTO;
import com.rest_jpa.entity.to.TaskTO;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.servise.CustomerOrderService;
import com.rest_jpa.servise.UserService;
import com.rest_jpa.servise.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private TaskService taskService;
    private CustomerOrderService customerOrderService;

    public UserFacadeImpl(UserService userService, TaskService taskService, CustomerOrderService customerOrderService) {
        this.userService = userService;
        this.taskService = taskService;
        this.customerOrderService = customerOrderService;
    }

    @Override
    public UserTO create(UserTO to) {
        User user = new User();
        setUserParametersFromTO(user, to);
        Long newUserId = userService.create(user).getId();
        to.setId(newUserId);
        return to;
    }

    @Override
    public void update(UserTO to) {
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
    public List<UserTO> findAll() {
        List<User> all = userService.findAll();
        return all.stream().map(UserTO::new).collect(Collectors.toList());
    }

    @Override
    public UserTO findById(long id) {
        User user = userService.findById(id);
        return new UserTO(user);
    }

    private void setUserParametersFromTO(User user, UserTO to) {
        user.setName(to.getName());
        user.setSurname(to.getSurname());
        user.setDepartment(to.getDepartment() != null ? Department.valueOf(to.getDepartment()) : null);
        user.setAddress(to.getAddress());
        user.setEmail(to.getEmail());
        user.setPhone(to.getPhone());
        if (!to.getTasks().isEmpty()) {
            user.setTasks(taskService.findAllByIds(to.getTasks().stream().map(TaskTO::getId).collect(Collectors.toList())));
        }
        if (!to.getOrders().isEmpty()) {
            user.setOrders(customerOrderService.findAllByIds(to.getOrders().stream().map(CustomerOrderTO::getId).collect(Collectors.toList())));
        }
    }
}

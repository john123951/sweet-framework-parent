package sweet.framework.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweet.framework.interceptor.annotation.Trace;
import sweet.framework.repository.dao.UserMapper;
import sweet.framework.repository.pojo.User;
import sweet.framework.service.IUserService;
import sweet.framework.service.dto.UserDto;

import java.util.Date;

@Service
@Trace
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Date getTime() {
        return new Date();
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);

            return userDto;
        }

        return null;
    }
}

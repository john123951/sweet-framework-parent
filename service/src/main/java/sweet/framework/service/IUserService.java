package sweet.framework.service;

import sweet.framework.service.dto.UserDto;

import java.util.Date;

/**
 * Created by sweet on 12/30/16.
 */

public interface IUserService {
    Date getTime();

    UserDto getUserById(Long userId);
}

package sweet.framework.repository.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import sweet.framework.repository.entity.User;
import sweet.framework.repository.entity.UserExample;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated Mon Jan 02 18:07:05 HKT 2017
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}
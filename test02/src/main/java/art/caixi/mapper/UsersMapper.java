package art.caixi.mapper;

import art.caixi.pojo.Users;

import java.util.List;

/**
 * 数据访问层接口
 */
public interface UsersMapper {
    // 查询所有用户信息
    List<Users> getAll();
    List<Users> getById(Integer id);
    List<Users> getByName(String name);
    int insert(Users user);
    int update(Users user);
    int delete(Integer id);
    List<Users> getByCondition(Users user);
}

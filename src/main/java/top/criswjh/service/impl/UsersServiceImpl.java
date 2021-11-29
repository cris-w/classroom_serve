package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.criswjh.entity.Users;
import top.criswjh.mapper.UsersMapper;
import top.criswjh.service.UsersService;

/**
 * @author wjh
 * @date 2021/11/29 6:26 下午
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}

package top.criswjh.listener;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.criswjh.common.exception.MyException;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.dto.UserDto;
import top.criswjh.service.SysUserService;

/**
 * EasyExcel 监听器 用于读取Excel 生成User对象
 *
 * @author wjh
 * @date 2022/1/5 9:47 PM
 */
public class UserExcelListener extends AnalysisEventListener<UserDto> {

    /**
     * 每隔50条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 50;
    List<SysUser> list = new ArrayList<>();

    // 由于UserExcelListener不能交给spring进行管理，需要自己new 不能注入其他对象，这样造成了操作数据库的不便，
    // 因此手动传入 SysUserService 对象用于操作数据库。

    private SysUserService sysUserService;
    private BCryptPasswordEncoder passwordEncoder;

    public UserExcelListener() {
    }

    public UserExcelListener(SysUserService sysUserService, BCryptPasswordEncoder passwordEncoder) {
        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if(exception instanceof MyException) {
            throw exception;
        }
    }

    /**
     * 一行一行读取Excel内容 注意：不包括表头
     *
     * @param user
     * @param analysisContext
     */
    @Override
    public void invoke(UserDto user, AnalysisContext analysisContext) {
        if (user == null) {
            throw new MyException(500, "文件数据为空");
        }

        if (sysUserService.nameExist(user.getUsername())) {
            throw new MyException(500, "用户名已存在");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setCreated(DateUtil.date());
        sysUser.setStatu(Const.STATUS_ON);
        // 默认密码 123456
        String pwd = passwordEncoder.encode(Const.DEFAULT_PASSWORD);
        sysUser.setPassword(pwd);
        // 默认头像
        sysUser.setAvatar(Const.DEFAULT_AVATAR);

        list.add(sysUser);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 读取完之后进行的操作
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }


    private void saveData() {
        sysUserService.saveBatch(list, BATCH_COUNT);
    }
}

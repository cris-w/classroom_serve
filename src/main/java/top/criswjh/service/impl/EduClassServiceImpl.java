package top.criswjh.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import top.criswjh.common.exception.MyException;
import top.criswjh.entity.EduClass;
import top.criswjh.service.EduClassService;
import top.criswjh.mapper.EduClassMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 */
@Service
public class EduClassServiceImpl extends ServiceImpl<EduClassMapper, EduClass>
        implements EduClassService {

    @Resource
    private EduClassMapper eduClassMapper;

    @Override
    public boolean createClass(EduClass eduClass) {
        if (isClassTitleExists(eduClass.getTitle().trim())) {
            throw new MyException(500, "班级已存在");
        }
        eduClass.setGmtCreate(DateUtil.date());
        int i = eduClassMapper.insert(eduClass);
        return i > 0;
    }

    @Override
    public boolean isClassTitleExists(String title) {
        return eduClassMapper.selectCount(
                new LambdaQueryWrapper<EduClass>().eq(EduClass::getTitle, title)) > 0;
    }

    @Override
    public boolean updateClass(EduClass eduClass) {
        EduClass old = eduClassMapper.selectById(eduClass.getId());
        String title = eduClass.getTitle().trim();
        if (!title.equals(old.getTitle()) && isClassTitleExists(title)) {
            throw new MyException(500, "班级已存在");
        }
        eduClass.setGmtUpdate(DateUtil.date());
        int i = eduClassMapper.updateById(eduClass);
        return i > 0;
    }
}





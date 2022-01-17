package top.criswjh.service;

import top.criswjh.entity.EduClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
*/
public interface EduClassService extends IService<EduClass> {

    /**
     * 创建班级
     *
     * @param eduClass
     * @return true 创建成功  false 创建失败
     */
    boolean createClass(EduClass eduClass);

    /**
     * 判断班级名是否存在
     *
     * @param title
     * @return true 存在 false 不存在
     */
    boolean isClassTitleExists(String title);

    /**
     * 跟新班级信息
     *
     * @param eduClass
     * @return true 更新成功 false 更新失败
     */
    boolean updateClass(EduClass eduClass);
}

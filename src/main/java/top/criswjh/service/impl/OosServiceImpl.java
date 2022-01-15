package top.criswjh.service.impl;

import cn.hutool.core.util.ReUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.service.OosService;
import top.criswjh.util.OosUtil;

/**
 * @author wjh
 * @date 2022/1/4 5:42 PM
 */
@Service
public class OosServiceImpl implements OosService {

    @Resource
    private OosUtil oosUtil;

    @Override
    public String uploadAvatar(MultipartFile file) {
        return oosUtil.upload(file);
    }

    @Override
    public Boolean deleteFile(String fileName) {
        return oosUtil.delete(fileName);
    }
}

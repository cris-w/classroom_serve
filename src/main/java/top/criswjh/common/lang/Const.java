package top.criswjh.common.lang;

/**
 * 常量
 *
 * @author wjh
 * @date 2021/12/3 7:16 下午
 */
public class Const {

    /**
     * 登录URL
     */
    public final static String LOGIN_URI = "/login";
    /**
     * 登录方法
     */
    public final static String LOGIN_METHOD = "POST";
    public final static String CAPTCHA_KEY = "captcha";
    /**
     * 请求头设置
     */
    public final static String REQUEST_HEADERS_CONTENT_TYPE = "application/json;charset=UTF-8";
    public final static String BAD_CREDENTIALS = "Bad credentials";
    /**
     * Redis 权限缓存
     */
    public final static String GRANTED_AUTHORITY = "GrantedAuthority: ";
    /**
     * 数据库数据的状态：正常
     */
    public final static Integer STATUS_ON = 1;
    /**
     * 数据库数据的状态：关闭
     */
    public final static Integer STATUS_OFF = 0;
    /**
     * 新增用户的默认密码
     */
    public final static String DEFAULT_PASSWORD = "123456";
    /**
     * 新增用户的默认头像
     */
    public final static String DEFAULT_AVATAR = "https://cdn.criswjh.top/tuanzi.png";
    /**
     * 角色：老师
     */
    public final static String TEACHER_ROLE = "教师";
}

package cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nnn
 * @since 2020-05-28
 */
@TableName("users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 用户名
     */
         @TableField("username")
    private String username;

        /**
     * 密码
     */
         @TableField("password")
    private String password;

        /**
     * 姓名
     */
         @TableField("name")
    private String name;

        /**
     * 电话
     */
         @TableField("phone")
    private String phone;

        /**
     * 身份证号
     */
         @TableField("idcard")
    private String idcard;

        /**
     * 身份
     */
         @TableField("role")
    private Integer role;

        /**
     * 删除标记
     */
         @TableField("isdel")
    private Integer isdel;


    public Integer getId() {
        return id;
    }

    public Users setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Users setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Users setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Users setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public Users setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public Users setRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Users setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Users{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", name=" + name +
        ", phone=" + phone +
        ", idcard=" + idcard +
        ", role=" + role +
        ", isdel=" + isdel +
        "}";
    }
}

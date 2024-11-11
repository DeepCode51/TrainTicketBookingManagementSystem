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
 * @since 2020-04-23
 */
@TableName("reply")
public class Reply extends Model<Reply> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 用户
     */
         @TableField("uid")
    private Integer uid;
         @TableField(exist=false)
         private Users user;
         
        public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		/**
     * 消息
     */
         @TableField("mid")
    private Integer mid;

        /**
     * 内容
     */
         @TableField("content")
    private String content;

        /**
     * 回复时间
     */
         @TableField("optime")
    private String optime;


    public Integer getId() {
        return id;
    }

    public Reply setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Reply setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getMid() {
        return mid;
    }

    public Reply setMid(Integer mid) {
        this.mid = mid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Reply setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Reply setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Reply{" +
        "id=" + id +
        ", uid=" + uid +
        ", mid=" + mid +
        ", content=" + content +
        ", optime=" + optime +
        "}";
    }
}

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
@TableName("site")
public class Site extends Model<Site> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 车次
     */
         @TableField("cid")
    private Integer cid;
         @TableField(exist=false)
         private Checi checi;
         

        public Checi getCheci() {
			return checi;
		}

		public void setCheci(Checi checi) {
			this.checi = checi;
		}

		/**
     * 站点名称
     */
         @TableField("sname")
    private String sname;

        /**
     * 到达时间
     */
         @TableField("atime")
    private String atime;

        /**
     * 时间
     */
         @TableField("stime")
    private Integer stime;


    public Integer getId() {
        return id;
    }

    public Site setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public Site setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public String getSname() {
        return sname;
    }

    public Site setSname(String sname) {
        this.sname = sname;
        return this;
    }

    public String getAtime() {
        return atime;
    }

    public Site setAtime(String atime) {
        this.atime = atime;
        return this;
    }

    public Integer getStime() {
        return stime;
    }

    public Site setStime(Integer stime) {
        this.stime = stime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Site{" +
        "id=" + id +
        ", cid=" + cid +
        ", sname=" + sname +
        ", atime=" + atime +
        ", stime=" + stime +
        "}";
    }
}

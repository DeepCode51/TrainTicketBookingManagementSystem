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
@TableName("checi")
public class Checi extends Model<Checi> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 车次
     */
         @TableField("name")
    private String name;

        /**
     * 车型
     */
         @TableField("type")
    private String type;

        /**
     * 起点
     */
         @TableField("bsite")
    private String bsite;

        /**
     * 终点
     */
         @TableField("esite")
    private String esite;

        /**
     * 发车时间
     */
         @TableField("btime")
    private String btime;

        /**
     * 到达时间
     */
         @TableField("etime")
    private String etime;

        /**
     * 座位
     */
         @TableField("isdel")
    private Integer isdel;

         @TableField(exist=false)
     private String sites;
         @TableField(exist=false)
         private String seats;  
         
         
         
    public String getSites() {
			return sites;
		}

		public void setSites(String sites) {
			this.sites = sites;
		}

		public String getSeats() {
			return seats;
		}

		public void setSeats(String seats) {
			this.seats = seats;
		}

	public Integer getId() {
        return id;
    }

    public Checi setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Checi setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Checi setType(String type) {
        this.type = type;
        return this;
    }

    public String getBsite() {
        return bsite;
    }

    public Checi setBsite(String bsite) {
        this.bsite = bsite;
        return this;
    }

    public String getEsite() {
        return esite;
    }

    public Checi setEsite(String esite) {
        this.esite = esite;
        return this;
    }

    public String getBtime() {
        return btime;
    }

    public Checi setBtime(String btime) {
        this.btime = btime;
        return this;
    }

    public String getEtime() {
        return etime;
    }

    public Checi setEtime(String etime) {
        this.etime = etime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Checi setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Checi{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", bsite=" + bsite +
        ", esite=" + esite +
        ", btime=" + btime +
        ", etime=" + etime +
        ", isdel=" + isdel +
        "}";
    }
}

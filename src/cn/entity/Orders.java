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
@TableName("orders")
public class Orders extends Model<Orders> {

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
        /**
     * 车次
     */
         @TableField("cid")
    private Integer cid;
         @TableField(exist=false)
         private Checi checi;

    @TableField("sid")
    private Integer sid;
    @TableField(exist=false)
    private Seat seat;
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
     * 票价
     */
         @TableField("price")
    private Double price;

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
     * 下单时间
     */
         @TableField("optime")
    private String optime;

    @TableField("status")
    private String status;


    public Integer getId() {
        return id;
    }

    public Orders setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Orders setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public Orders setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public Integer getSid() {
        return sid;
    }

    public Orders setSid(Integer sid) {
        this.sid = sid;
        return this;
    }

    public String getBsite() {
        return bsite;
    }

    public Orders setBsite(String bsite) {
        this.bsite = bsite;
        return this;
    }

    public String getEsite() {
        return esite;
    }

    public Orders setEsite(String esite) {
        this.esite = esite;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Orders setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getBtime() {
        return btime;
    }

    public Orders setBtime(String btime) {
        this.btime = btime;
        return this;
    }

    public String getEtime() {
        return etime;
    }

    public Orders setEtime(String etime) {
        this.etime = etime;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Orders setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Orders setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Orders{" +
        "id=" + id +
        ", uid=" + uid +
        ", cid=" + cid +
        ", sid=" + sid +
        ", bsite=" + bsite +
        ", esite=" + esite +
        ", price=" + price +
        ", btime=" + btime +
        ", etime=" + etime +
        ", optime=" + optime +
        ", status=" + status +
        "}";
    }

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Checi getCheci() {
		return checi;
	}

	public void setCheci(Checi checi) {
		this.checi = checi;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
    
    
}

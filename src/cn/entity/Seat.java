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
@TableName("seat")
public class Seat extends Model<Seat> {

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
     * 站点
     */
         @TableField("sid")
    private Integer sid;
         @TableField(exist=false)
         private Site site;
         
        public Site getSite() {
			return site;
		}

		public void setSite(Site site) {
			this.site = site;
		}

        /**
     * 座位类型
     */
       
         @TableField("name")
    private String name;

        /**
     * 价格
     */
         @TableField("price")
    private Double price;

        /**
     * 票数
     */
         @TableField("num")
    private Integer num;


    public Integer getId() {
        return id;
    }

    public Seat setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public Seat setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public Integer getSid() {
        return sid;
    }

    public Seat setSid(Integer sid) {
        this.sid = sid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Seat setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Seat setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public Seat setNum(Integer num) {
        this.num = num;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Seat{" +
        "id=" + id +
        ", cid=" + cid +
        ", sid=" + sid +
        ", name=" + name +
        ", price=" + price +
        ", num=" + num +
        "}";
    }
}

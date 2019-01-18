/**
 * 
 */
package bean;

/**
 * CREATE TABLE `type` ( `id` int(11) NOT NULL AUTO_INCREMENT, `type`
 * varchar(45) NOT NULL, `remark` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`),
 * UNIQUE KEY `idtype_UNIQUE` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT
 * CHARSET=utf8 COMMENT='题目类型：选择题、填空题、阅读题、改错题。'
 * 
 * @author MJCoder
 *
 */
public class Type {
	private int id;
	private String type;
	private String remark;

	/**
	 * 
	 */
	public Type() {
		// TODO Auto-generated constructor stub
	}

	public Type(int id, String type, String remark) {
		super();
		this.id = id;
		this.type = type;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + ", remark=" + remark + "]";
	}

}

/**
 * 
 */
package bean;

/**
 * CREATE TABLE `level` ( `id` int(11) NOT NULL AUTO_INCREMENT, `level`
 * varchar(45) NOT NULL, `remark` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`),
 * UNIQUE KEY `id_UNIQUE` (`id`), UNIQUE KEY `level_UNIQUE` (`level`) )
 * ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='级别：小学、初中、高中'
 * 
 * @author MJCoder
 *
 */
public class Level {
	private int id;
	private String level;
	private String remark;

	/**
	 * 
	 */
	public Level() {
		// TODO Auto-generated constructor stub
	}

	public Level(int id, String level, String remark) {
		super();
		this.id = id;
		this.level = level;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Level [id=" + id + ", level=" + level + ", remark=" + remark + "]";
	}

}

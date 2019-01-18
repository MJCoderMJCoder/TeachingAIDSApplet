/**
 * 
 */
package bean;

/**
 * CREATE TABLE `user` ( `id` int(11) NOT NULL AUTO_INCREMENT, `name`
 * varchar(45) NOT NULL, `password` varchar(45) NOT NULL, `account` varchar(45)
 * NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `iduser_UNIQUE` (`id`) )
 * ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表'
 * 
 * @author MJCoder
 *
 */
public class User {
	private int id;
	private String name;
	private String password;
	private String account;
	private Level level; // 为了方便操作，新增用户界面字段
	private Type type; // 为了方便操作，新增用户界面字段

	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String password, String account) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", account=" + account + ", level="
				+ level + ", type=" + type + "]";
	}
}

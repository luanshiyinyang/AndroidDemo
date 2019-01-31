# GreenDAO的使用
- 简介
	- 安卓提供了一个占用内存极小的数据库SQLite，也提供了不少操作数据库的API，然而不是所有程序员都擅长编写SQL语句，这时一个ORM的数据库框架就显得很好用了。
	- 在[之前的博客](https://blog.csdn.net/zhouchen1998/article/details/86667520)我已经提到了LitePal的使用，但是实际开发中使用的LitePal的项目并不多，相反很多时候GreenDAO是一个不错的选择。
	- 当然，现在的开发很多至关重要的用户数据都是部署在服务器上的大型数据库中，但是即使如此也有很多不方便远程部署的对象需要本地数据库来存储，对SQLite的操作就比较重要了。
- 特点
	- 性能最大化，可能是Android平台上最快的ORM框架
	- 易于使用的API
	- 最小的内存开销
	- 依赖体积小
	- 支持数据库加密
	- 强大的社区支持
- 使用
	- 配置
		- Project下的build.gradle修改如下
			- repositories项中添加一句
				- `mavenCentral()//add repository`
			- dependencies项中添加一句
				- `classpath 'org.greenrobot:greendao-gradle-plugin:3.2.0' //add plugin`
		- app下的build.gradle修改
			- 顶部添加一个plugin的应用
				- `apply plugin: 'org.greenrobot.greendao'//apply plugin`
			- dependencies项中添加一句
				- `implementation 'org.greenrobot:greendao:3.2.0'//add library`
		- app下的build.gradle中添加一项如下
			- ```gradle
				greendao{
				    schemaVersion 1 // 数据库版本号
    					daoPackage  'com.zc.greendao'//greenDao 自动生成的代码保存的包名
    					targetGenDir   'src/main/java' //自动生成的代码存储的路径，默认是 build/generated/source/greendao.
    					generateTests false //true的时候自动生成测试单元
    					//targetGenDirTests: src/androidTest/java //测试单元的生成目录
				}
				```
	- 使用
		- 编写实体类对应一个表
			- 代码见GitHub
			- 在Entity中可以配置很多信息
				- schema：当前实体属于哪个数据库
				- active：标记一个实体处于活跃状态，活动实体有更新、删除和刷新方法
				- nameInDb：在数据库中使用的别名，默认使用的是实体的类名
				- indexes：定义索引，可以跨越多个列
				- createInDb：标记创建数据库表
		- Make Project会生成相关代码在指定位置（具体一句上面的greendao项设置）
			- ![](https://img-blog.csdnimg.cn/20190131115157597.png)
			- DaoMaster
				- greenDao的入口，持有数据库对象并且管理数据库的DAO类。持有静态的创建于删除表的方法。内部类OpenHelper以及DevOpenHelper实现了SQLiteOpenHelper，创建了数据库模式（Schema），进行数据库连接。
			- DaoSession
				- 管理特定模式的所有可用的DAO对象，通过getter方法可以获取到DAO对象。提供了增删查改实体的方法。
			- DAOs
				- 全称Data access Objects，数据访问对象，对于每一个实体类，greenDao生成一个DAO,持有很多持久性的方法，例如count，loadAll以及insertInTx。如图，生成了Teacher类的Dao。
		- 初始化以及增删查改
			- 这里为了演示方便均在MainActivity中进行。
			- 初始化数据库。一般在Application类中进行，且只进行一次。（关于Application类的创建使用不介绍。
				- 代码见MainActivity.java。
			- 插入
				- insert(Teacher entity)
					- 插入一个实体记录到数据库。
				- 注意，id传入long型，传入null则默认自增长。
				- 代码见MainActivity.java。
			- 删除
				- deleteBykey(Long key) 
					- 根据主键删除一条记录。
				- delete(Teacher entity) 
					- 根据实体类删除一条记录，一般结合查询方法，查询出一条记录的对象之后删除。
				- deleteAll()
					- 删除所有记录。
				- 代码见MainActivity.java。
			- 查询
				- loadAll()
					- 查询所有记录
				- load(Long key)
					- 根据主键查询一条记录
				- queryBuilder().list()
					- 返回一个List
				- queryBuilder().where(TeacherDao.Properties.Name.eq("")).list()
					- 返回一个List
				- queryRaw(String where,String selectionArg)
					- 返回一个List
			- 修改
				- update(Teacher entity)
					- 根据对象更新一条记录
- 补充说明
	- 具体完整项目代码见我的GitHub，欢迎star或者fork
			
			
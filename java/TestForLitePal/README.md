# LitePal的使用
- 背景
	- 安卓内置了一个轻量数据库SQLite，然而很多时候使用SQLite是不方便的，更多开发者习惯服务器部署MySQL之类的数据库，而且复杂的SQL语言对于很多没有系统学习过数据库和关系数学的开发者来说略有难度。
	- 这个时候ORM模型的出现就应运而生了，一个类对应一个表，一个对象对应表中的一条记录，增删查改功能全部帮你封装好，这是个开源的时代了。
	- 常用的ORM安卓框架有OrmLite、SugarORM、GreenDAO、Active Android、Realm以及本案例讲解的LitePal。
	- 注意：如果数据库访问频繁、追求性能，那么GreenDAO将会是一个不错的选择，如果数据库比较简单，LitePal足够了。因为LitePal本质上使用的还是SQLite。
- 简介
	- LitePal是一款开源的Android数据库框架，它采用了对象关系映射（ORM）的模式，并将我们平时开发最常用到的一些数据库功能进行了封装，使得不用编写一行SQL语句就可以完成各种建表和増删改查的操作。详细内容可以查看[官方文档](https://github.com/LitePalFramework/LitePal)。
- 使用
	- 配置
		- 如今大部分开源项目都会提交到jcenter上，只需要在app/build.gradle中dependencies中添加一项如下。
			- `compile 'org.litepal.android:java:3.0.0'`
		- 比较新的版本如下。
			- `implementation 'org.litepal.android:java:3.0.0'`
		- 当然，如果你使用的是Kotlin，则添加的依赖如下。
			-  `implementation 'org.litepal.android:kotlin:3.0.0'`
		- 下面就是配置文件的编写。
			- 在app/src/main目录中新建一个目录assets，在assets目录下新建litepal.xml文件，内容如下。
				- ```
				<?xml version="1.0" encoding="utf-8"?>
					<litepal>
					<dbname value="Test" ></dbname>
					<version value="1" ></version>
					<list>
					</list>
					</litepal>
				```
			- 其中，<dbname> 标签用于指定数据库名，<version> 标签用于指定数据库版本号，<list>标签用于指定所有的映射模型。
			- 配置LitePalApplication，编辑AndroidManifest.xml文件在application标签添加一行内容如下。
				- android:name="org.litepal.LitePalApplication"
	- 建表并放入数据库
		- 在MainActivity.java同级目录新建bean文件夹，创建一个类为Teacher。
			- 代码如下。
		- 修改litepal.xml添加表到数据库（即在list标签内添加下面一行）。后面每个数据库类的建立都要加入一个mapping。
			-  `<mapping class="com.zc.testforlitepal.bean.Teacher"></mapping>`
		- 现在，任意一个数据库操作都会创建数据库。
	- 更新数据库
		- 现在向数据库中Teacher表添加一个字段，只需要修改Teacher类中内容，然后litepal.xml文件中version自增1即可。
	- 数据库操作
		- 数据库操作需要继承LitePalSupport类。
		- 插入（insert）
			- 添加一个记录只需要创建一个对象并且save即可。（目前还看不到效果，后面查询时就知道了）
		- 更新（update）
			- 修改已存储对象即可。一种save后对原对象修改。
			- 一种利用where约束修改原内容。前者实用性极差不做尝试。
		- 删除（delete）
			- 一种调用save的对象的delete方法即可。
			- 使用LitePal.deleteAll方法或者delete方法。
		- 查询（query）
			- 一种方法调用find方法。
				- `Song song = LitePal.find(Song.class, id);`
			- 一种方法利用条件查询到结果集合。
				- `List<Song> allSongs = LitePal.findAll(Song.class);`
				- `List<Song> songs = LitePal.where("name like ? and duration < ?", "song%", "200").order("duration").find(Song.class);`
- 补充说明
	- MainActivity.java代码
		- 如下
	- 参考书《第一行代码》
	- Kotlin代码与java类似，可见官网
	- 具体代码可以查看我的GitHub，欢迎star或者fork
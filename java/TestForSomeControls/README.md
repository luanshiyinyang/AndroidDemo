# 常用UI控件
- 简介
	- 这一篇介绍开发中的常用UI控件。
- 布局管理器
	- 所有布局管理器都是ViewGroup的子类，都可作为容器类使用。继承自View，所以也可嵌套。
	- 常见的布局之前已经提到了三种，这里不再提，只需要知道，它一般作为控件的容器，利用布局管理器方便布局的开发。
- TextView及其子类
	- Textview文本框 
		- 显示文本内容的文本区域，不可编辑。
	- EditText编辑框
		- 显示文本内容，可编辑修改。
	- Button按钮
		- 可点击的一个按钮，点击触发onclick事件，其实是TextView的点击实现。在开发中，无论装饰得多么花哨，很多控件的本质依然是Button。
		- RadioButton：单选按钮，一般和RadioGroup一起使用组成选项组
		- CheckBox：复选按钮	
		- ToggleButton：状态开关按钮
		- Switch：开关  
	- Clock钟
		- TextClock：取代DigitalClock组件，能以24/12 小时制来显示时间，数字。
		- AnalogClock：继承自View组件，overwrite了View的OnDraw方法，它会在View上绘制模拟时钟。使用这个控件IDE加底线，表示不建议使用了。
	- Chronometer计时器
		- 倒计时。
	- 代码测试
		- ```xml
			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    tools:context="com.zc.helloworld.MainActivity"
			    android:orientation="vertical"
			    >
			    <TextView
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个TextView"/>
			    <EditText
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个EditText"/>
			    <Button
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个Button"/>
			    <RadioButton
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个RadioButton"/>
			    <CheckBox
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个CheckBox"/>
			    <ToggleButton
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content" />
			    <Switch
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:text="这是一个Switch"/>
			    <TextClock
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content" />
			    <AnalogClock
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content" />
			    <Chronometer
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content" />
			</LinearLayout>
			```
	- 演示结果
		- ![](https://img-blog.csdnimg.cn/20190201141130692.png)
- ImageView及其子类
	- 继承自View，显示图片。
	- ImageButton图片按钮
		- 图片按钮，可触发。
	- QuickContactBadge关联联系人图片
		- 显示关联到特定联系人的图片。
	- ZoomButton图片缩放按钮
		- 代表”放大”、”缩小”两个按钮。（需要代码控制）
	- 代码测试
		- ```xml
			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    tools:context="com.zc.helloworld.MainActivity"
			    android:orientation="vertical"
			    >
			    <ImageView
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:src="@mipmap/ic_launcher"/>
			    <ImageButton
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:src="@mipmap/ic_launcher"/>
			    <QuickContactBadge
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:src="@mipmap/ic_launcher"/>
			    <ZoomButton
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:src="@mipmap/ic_launcher"/>
			</LinearLayout>
			```
	- 演示结果
		- ![](https://img-blog.csdnimg.cn/20190201141744309.png)
- AdapterView及子类
	- 本身是一个抽象基类，继承了ViewGroup，它的本质是容器，它无法直接使用，是一种自动适配的容器。
	- Listview列表视图
		- 垂直显示列表内容的控件。
	- Adapter适配器
		- 用内容填充列表视图。
	- AutoCompleteTextView自动完成文本框
		- AutoCompleteTextView由Editext派生，实际上它也是一个编辑框，但它比普通编辑框多了一个功能：当用户输入一定字符之后，自动完成文本框会显示一个下拉菜单，供用户从中选择。
	- GridView网格视图
		- 类似于ListView，但是多列。
	- ExpandableListView可展开的列表组件
		- 列表项分组。
	- 这一部分均需要适配器，后面博客提到，就不演示了。
- ProgressBar及其子类
	- ProgressBar进度条
		- 进度条通常用于向用户显示一个百分比。进度条可以动态地显示进度，避免用户死等的尴尬状态。用颜色填充表明进度。
	- SeekBar拖动条
		- 拖动条和进度条非常相似，通过滑块的位置来标识数值，允许用户拖动滑块来改变值，因此拖动条通常用于对系统的某种数值进行调节，亮度调节的底层就是它。
	- RatingBar星级评分条
		- 允许用户通过拖动来改变进度，不过RatingBar通过星星来表示进度。
	- 代码测试
		- ```xml
			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    tools:context="com.zc.helloworld.MainActivity"
			    android:orientation="vertical"
			    >
			   <ProgressBar
			       android:layout_width="match_parent"
			       android:layout_height="wrap_content" />
			    <SeekBar
			        android:layout_width="match_parent"
			        android:layout_height="wrap_content" />
			    <RatingBar
			        android:layout_width="match_parent"
			        android:layout_height="wrap_content" />
			</LinearLayout>
			```
	- 演示结果
		- ![](https://img-blog.csdnimg.cn/20190201142241820.png)
- 对话框
	- 以下对话框均需要具体代码实现逻辑，后面介绍，这里只演示效果。
	- AlertDialog弹出对话框
		- 功能最丰富、实际应用最广的对话框
	- ProgressDialog进度条对话框
		- 进度条对话框、这个对话框只是对进度条的包装
	- DatePickerDialog日期选择对话框
		- 日期选择对话框，这个对话框只是对DatePicker的包装
	- TimePickerDialog时间选择对话框
		- 时间选择对话框，这个对话框只是对TimePicker的包装。
	- 代码测试
		- 一般代码控制弹出，演示需要，我让它显示出来了。
		- ```xml
			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    tools:context="com.zc.helloworld.MainActivity"
			    android:orientation="vertical"
			    >
			    <ProgressBar
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content" />
			    <TimePicker
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"></TimePicker>
			    <TimePicker
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"></TimePicker>
			</LinearLayout>
			```
	- 演示结果
		- ![](https://img-blog.csdnimg.cn/20190201142810958.png)
- 菜单（Menu）
	- Android提供了两种创建菜单的方式，一种是代码创建，一种是使用xml资源文件定义。推荐后者，即用xml文件方式，可以减少代码量。
	- 一般而言，菜单资源文件放在res目录的menu目录下（没有规定，但约定俗成），其xml文件的根元素通常是<menu../>，不需要指定任何属性。
		- 资源文件内容：
			- <item../> 元素：定义菜单项
			- <group../>子元素：将多个<item../>定义的菜单项包装成一个菜单组。
		- 在文件中定义了菜单资源后，必须重写onCreateOptionsMenu()，onCreateContextMenu() 方法，在这些方法中调用MenuInflater对象的inflate方法（填充器填充）使用指定资源文件对应的菜单就可以了。
- 活动条( ActionBar )
	- 安卓3.0出现，位于传统的标题栏的位置，也就是显示在屏幕的顶部。其上一般显示App的图标和Activity标题。除此之外，ActionBar的右边海可以显示活动项( Action Item )。
	- 但是我们一般不使用这种，所以我在前面的博客都是NoActionBar。
- Toast信息提示
	- 显示提示信息，如今的APP中最常见的是“再点击一次退出”。（开发是用于事件是否成功的测试）
	- 代码测试
		- ```java
			package com.zc.helloworld;
			
			import android.content.Context;
			import android.support.v7.app.AppCompatActivity;
			import android.os.Bundle;
			import android.view.View;
			import android.widget.Button;
			import android.widget.Toast;
			
			
			public class MainActivity extends AppCompatActivity {
			
			    private Button btn;
			    private Context context;
			
			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        setContentView(R.layout.activity_main);
			        btn = (Button) findViewById(R.id.btn);
			        context = this;
			        btn.setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View v) {
			                Toast.makeText(context,"安卓",Toast.LENGTH_LONG).show();
			            }
			        });
			
			    }
			
			
			}
- SearchView搜索框
	- 可以让用户在文本框输入文字，通过监听器监控用户输入，当用户输入完成后提交搜索，也可通过监听器执行实际的搜索功能实现。
	- 代码测试
		- ```xml
			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    tools:context="com.zc.helloworld.MainActivity"
			    android:orientation="vertical"
			    >
			
			<SearchView
			    android:layout_width="match_parent"
			    android:layout_height="wrap_content"></SearchView>
			
			</LinearLayout>
			```
- Fragment碎片
	- 3.0版本引入了Fragment功能，类似于Activity，可以像Activity一样包含布局。 不妨认为Fragment是一种轻量级的Activity，引入Fragment后，一个屏幕下布局更有了定制性和扩展性。曾今麻烦的多标签实现有了更方便的用法。
	- 这是一个相当常用的控件，很多实现都是基于它。
	- 例如QQ的底部菜单，美团外卖的底部菜单导航。
- 补充说明
	- 具体代码可以在我的Github找到，欢迎star或者fork
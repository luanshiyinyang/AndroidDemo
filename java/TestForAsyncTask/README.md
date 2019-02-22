# 利用AsyncTask和OkHttp进行API数据请求
- 背景
	- 从安卓3.0开始，不允许在主线程和UI线程中进行网络请求，因为可能会造成线程堵塞。
	- 因此，只能在子线程进行网络请求，出现了很多请求的方法，例如开启子线程利用Message传递数据，或者OkHttp封装了异步请求，会开启新线程进行网络请求。
	- 本案例使用OkHttp（主要因为方便使用，比起HttpConnection和HttpClient效率不遑多让，甚至仍有胜之）进行网络请求，但是使用同步请求（不会开启新的线程，因此会崩溃）。
	- 本案例，处理网络请求的方式为异步加载处理，利用AsyncTask开启一个异步任务，任务返回结果并进行处理操作。
- 配置
	- 添加okhttp和gson的依赖
		- `implementation 'com.squareup.okhttp3:okhttp:3.10.0'`
		- `implementation 'com.google.code.gson:gson:2.8.2'`
- 介绍
	- 一般，将任务封装为一个类并继承AsyncTask<Params, Progress,Result>。继承AsyncTask需要指定三个泛型参数。
		- Params：启动任务时输入参数的类型,我这里传入了一个URL路径。
		- Progress：后台任务执行中返回进度值的类型，可以用来显示进度。
		- Result：后台任务完成后返回结果的类型，我这里是一个自定义的Item。
	- 如果你用的IDE是比较智能的如Android Studio，那么会提示实现相应的方法，其中doInBackGround是必须实现的，也是唯一一个子线程的方法。
		- doInBackGround：必须重写，子线程在这个方法里面进行耗时操作。（这个方法的进行在子线程中）
		- onPreExecute：执行后台耗时操作前被调用，通常在方法里面进行一些初始化的操作。
		- onPostExecute：当doInBackground完成后，系统会自动调用这个方法，并且将doInBackground方法返回的值传给该方法。
		- onProgressUpdate：在doInBackground方法中调用publishProgress方法时，更新任务的执行进度后，会触发这个方法。
- 实战使用
	- 首先需要在manifest文件中添加网络权限。
	- 使用OkHttp+AsyncTask发送网络请求并使用Gson解析API数据，渲染到界面的一个TextView上。
	- 简单编写一个只含有name属性的Bean类。
		- ![](https://img-blog.csdnimg.cn/20190222153611928.png)
	- 编写Task类，继承自AsyncTask并在后台使用OkHttp进行网络请求。请求的是自己编写的一个API网站（API的WEB服务搭建这里不做叙述，请求得到的是json格式数据。）。
		- 请求的数据结构
			- ![](https://img-blog.csdnimg.cn/20190222170914389.png)
		- ```python
			package com.zc.testforasynctask;
			
			import android.content.Context;
			import android.os.AsyncTask;
			import android.util.Log;
			import android.widget.TextView;
			import android.widget.Toast;
			
			import com.google.gson.Gson;
			
			import okhttp3.OkHttpClient;
			import okhttp3.Request;
			import okhttp3.Response;
			
			public class MyTask extends AsyncTask<String, String, Item> {
			    private Context mContext;
			    private TextView tv;
			
			    public MyTask(Context mContext, TextView tv) {
			        this.mContext = mContext;
			        this.tv = tv;
			    }
			
			    @Override
			    protected void onPreExecute() {
			        super.onPreExecute();
			        Toast.makeText(mContext, "开始寻找网络资源", Toast.LENGTH_SHORT).show();
			    }
			
			    private String baseUrl = "http://13.250.1.159:8000/api/";
			    @Override
			    protected Item doInBackground(String... strings) {
			        Item result;
			        try {
			            Thread.sleep(0b1111101000);
			            OkHttpClient client = new OkHttpClient();
			            String nowUrl = baseUrl + "merchants/" + strings[0] + ".json";
			            Request request = new Request.Builder().url(nowUrl).build();
			            Response response = client.newCall(request).execute();
			            if (response.isSuccessful()){
			                result = parseItemJsonObject(response.body().string());
			                Log.i("msg", result.getName());
			            }
			            else {
			                Toast.makeText(mContext, "无结果", Toast.LENGTH_SHORT).show();
			                return null;
			            }
			        }catch (Exception e){
			            Toast.makeText(mContext, "网络连接异常", Toast.LENGTH_SHORT).show();
			            return null;
			        }
			        return result;
			
			    }
			
			    @Override
			    protected void onPostExecute(Item item) {
			        super.onPostExecute(item);
			        if(item != null){
			            tv.setText(item.getName());
			        }
			        else {
			            tv.setText("数据解析为空");
			        }
			
			    }
			
			    private  Item parseItemJsonObject(String jsonData) {
			        // 解析Json对象
			        try {
			            if (jsonData != null) {
			                //创建一个Gson对象
			                Gson gson = new Gson();
			                Item item = gson.fromJson(jsonData,Item.class);
			                return item;
			            }
			            else {
			                return null;
			            }
			
			        }catch (Exception e){
			            e.printStackTrace();
			            return null;
			        }
			    }
			}
			
			```
	- 主活动进行事件绑定和点击事件注册
		- 代码见GitHub
- 效果展示
	- ![](https://img-blog.csdnimg.cn/2019022217104948.gif)
- 补充说明
	- 具体代码和环境配置见我的GitHub，欢迎star或者fork
	- 欢迎指出优化之处
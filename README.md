#Common4Android v2.0

  Common methods for android developer v2.0.

##Overview
###-基类
ClassName                  | Description  
---------------------------|--------------------------------------
BaseApplication.java       | Application基类，启动SDCard监听、网络状态监听。
BaseActiivty.java          | Activity基类，封装常用方法及Activity管理。

###-管理器                       
ClassName                  | Description  
---------------------------|--------------------------------------
ActivityManager.java       | Activity管理工具类，可以获得当前引用启动的Activity实例。
HotFixManager.java         | 热修复patch加载工具类。
SystemBarTintManager.java  | 沉浸式管理。
ThemeSettingsHelper.java   | 主题模式切换管理（日间/夜间模式）。
LRUCache.java              | LRUCache。
ThreadPoolManager.java     | 应用线程池管理。

###-网络处理                       
ClassName                  | Description  
---------------------------|--------------------------------------
HttpTag.java               | Api接口配置类。
TaskManager.java           | 网络任务管理类。
HttpDataRequest.java       | 通用请求类。
HttpGetRequest.java        | Get请求类。
HttpPostRequest.java       | Post请求类。

###-工具类
ClassName                  | Description  
---------------------------|--------------------------------------
AnimationUtil.java         | 动画效果工具类，提供缩放、透明度、位移、旋转动画方法。
AppInfoUtil.java           | 应用信息工具类，获取应用版本号、版本编码。
BitmapCacheUtil.java       | Bitmap缓存工具类，封装bitmap本地存储方法。
BitmapEffectUtil.java      | Bitmap特效实现类，封装bitmap特效实现方法，如：老照片、RGB偏移等。
BitmapUtil.java            | Bitmap常用工具类，Bitmap数据类型转换、圆角、缩放、倒影。
ConvertUtil.java           | 转换工具类，进行对象的类型转换。
DateUtil.java              | 日期工具类，日期转换生肖、日期转换星座、日期相互转换。
DesUtil.java               | DES加密工具类。
DeviceUtil.java            | 设备信息获取工具类，获得设备型号、设备生产厂商、屏幕尺寸、GPS状态、wifi状态等。 
DialogUtil.java            | 弹窗工具类，ProgressDialog，AlertDialog，Toast弹出封装。
FileUtil.java              | 文件工具类，文件常用方法，获得文件大小、文件大小转换。
MD5Util.java               | MD5加密工具类。
RegexUtil.java             | 常用正则表达式工具类。
SDCardUtil.java            | SD卡信息管理工具类。
SharedPreferencesUtil.java | SharedPreferences工具类。 
StringUtil.java            | 字符串处理工具类。
SystemIntentUtil.java      | 系统Intent工具类，常用的系统Intent跳转函数，如：打电话、发短信等。 

##Example Usage

### - NetWork
#### Config HttpTag
	public enum HttpTag {

		TEST1(Constants.TAG_TYPE_STRING, 1, "http://www.baidu.com", null),
		TEST2(Constants.TAG_TYPE_JSON_OBJECT, 2, "http://www.baidu.com", null),
		TEST3(Constants.TAG_TYPE_JSON_ARRAY, 3, "http://www.baidu.com", null),
		TEST4(Constants.TAG_TYPE_GSON, 4, "http://www.baidu.com", Object.class);

		/**
		 * HttpTag
		 * @param tagType 标签类型 1:string, 2:gson, 3:jsonObject, 4:jsonArray
		 * @param httpTag Tag标签，用于接收响应数据
		 * @param httpUrl 请求Url
		 * @param parseClass GSON类型使用，用于转换GSON数据
		 */
		HttpTag(int tagType, int httpTag, String httpUrl, Class<?> parseClass) {
			this.mTagType = tagType;
			this.mHttpTag = httpTag;
			this.mHttpUrl = httpUrl;
			this.mParseClass = parseClass;
		}

		private final int mTagType;
		private final int mHttpTag;
		private final String mHttpUrl;
		private final Class<?> mParseClass;


		public int getTagType() {
			return mTagType;
		}

		public int getHttpTag() {
			return mHttpTag;
		}

		public String getHttpUrl() {
			return mHttpUrl;
		}

		public Class<?> getParseClass() {
			return mParseClass;
		}

		@Override
		public String toString() {
			String toString = "[tag=" + mHttpTag + "][url=" + mHttpUrl + "][parseClass=" + mParseClass + "]";
			return toString;
		}

	}
#### Create a Get Request
	HttpGetRequest request = new HttpGetRequest();
	request.setTag(HttpTag.TEST);
	request.setSort(Constants.REQUEST_METHOD_GET);
	request.setGzip(true);
	request.setRetry(false);
	request.setNeedAuth(false);
	TaskManager.startHttpDataRequset(request, new HttpDataResponse() {
			
		@Override
		public void onHttpRecvOK(HttpTag tag, Object extraInfo, Object result) {
			DialogUtil.showToast(MainActivity.this, (String) result, Toast.LENGTH_LONG);
		}
			
		@Override
		public void onHttpRecvError(HttpTag tag, HttpCode retCode, String msg) {
			DialogUtil.showToast(MainActivity.this, "onHttpRecvError retCode:" + retCode + " msg:" + msg, Toast.LENGTH_LONG);
		}
			
		@Override
		public void onHttpRecvCancelled(HttpTag tag) {
			DialogUtil.showToast(MainActivity.this, "onHttpRecvCancelled", Toast.LENGTH_LONG);
		}
	});
	
#### Create a Post Request
	HttpPostRequest request = new HttpPostRequest();
	request.setTag(HttpTag.TEST);
	request.setSort(Constants.REQUEST_METHOD_POST); // application/x-www-form-urlencoded
	//request.setSort(Constants.REQUEST_METHOD_POST_MULTIPLE); // multipart/form-data
	request.setGzip(true);
	request.setRetry(false);
	request.setNeedAuth(false);
	TaskManager.startHttpDataRequset(request, new HttpDataResponse() {
			
		@Override
		public void onHttpRecvOK(HttpTag tag, Object extraInfo, Object result) {
			DialogUtil.showToast(MainActivity.this, (String) result, Toast.LENGTH_LONG);
		}
			
		@Override
		public void onHttpRecvError(HttpTag tag, HttpCode retCode, String msg) {
			DialogUtil.showToast(MainActivity.this, "onHttpRecvError retCode:" + retCode + " msg:" + msg, Toast.LENGTH_LONG);
		}
			
		@Override
		public void onHttpRecvCancelled(HttpTag tag) {
			DialogUtil.showToast(MainActivity.this, "onHttpRecvCancelled", Toast.LENGTH_LONG);
		}
	});

#### NetWork add parameters & file upload

	request.addUrlParams(key, value);  // URL params
	request.addHeadParams(key, value); // Head params
	request.addBodyParams(key, value); // Body params only post
	request.addFileParams(key, file);  // File params only post
  
### - HotFixManager

#### 1. Generate patch.jar with [APKTool](https://github.com/widebluesky/Common4Android-APKTool#patch操作热更新补丁生成).
#### 2. Load patch.jar.

	String patchVersion = "0.1.0";
	String patchPath = "SDCard://mnt/common4android/path/patch.jar";
	HotFixManager hotFixManager = new HotFixManager(this);
	hotFixManager.init(patchVersion);  // init with patch version
	hotFixManager.addPatch(patchPath); // load patch.jar
	
## Contact

  E-mail：widebluesky@qq.com
  
  WeChat：widebluesky
  
<p align="center" >
  <img width="300" height="300" src="https://raw.githubusercontent.com/widebluesky/Common4Android/master/wechat_qrcode.jpg" alt="Common4Android" title="Common4Android">
</p>
  
  

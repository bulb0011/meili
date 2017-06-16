package www.weimeng.com.meili.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import www.weimeng.com.meili.R;

import static android.R.attr.x;


public class RollViewPager extends ViewPager {


	private List<String> titleList;
	private TextView tvDesc;
	private List<String> imageUrlList;
	private List<ImageView> pointList;
	private  Context ctx;

	/**
	 * XUtils中的一个工具，
	 */
//	private x.BitmapUtils bitmapUtils;

	public RollViewPager(Context context) {
		super(context);
		this.ctx=context;
		this.setOnPageChangeListener(pageChangeListener);

	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			// 改变描述文字
//			tvDesc.setText(titleList.get(position));
			// 将上一个点复原
			pointList.get(lastPosition).setBackgroundResource(R.mipmap.dot_normal);
			// 改变指示点
			pointList.get(position).setBackgroundResource(R.mipmap .dot_focus);

			lastPosition = position;
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
								   int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	};

	/**
	 * 页面改变时，上一个页面的位置
	 */
	private int lastPosition;

	/**
	 * 设置轮播图的描述文字
	 * @param titleList
	 * @param top_news_title
	 */
	public void setTitles(List<String> titleList, TextView tvDesc) {
		this.titleList = titleList;
		this.tvDesc = tvDesc;
//		tvDesc.setText(titleList.get(0)); // 设置默认的文字
	}

	/**
	 * 设置轮播图的，图片URL地址
	 * @param imageUrlList
	 */
	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

	/**
	 * 设置指示点的集合
	 * @param pointList
	 */
	public void setPointList(List<ImageView> pointList) {
		this.pointList= pointList;

	}

	/**
	 * 开始滚动
	 */
	public void startRoll() {

		if(adapter == null){
			adapter = new MyAdapter();
			this.setAdapter(adapter);
		}else{
			adapter.notifyDataSetChanged();
		}

		isRunning = true;
		//TODO
		handler.removeMessages(FLUSH);
		handler.sendEmptyMessageDelayed(FLUSH, 2000);

	}

	private final int FLUSH = 100;

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case FLUSH:
					if(isRunning){
						// 让viewPager切换至下一页
						setCurrentItem((getCurrentItem()+1)%imageUrlList.size());
						handler.sendEmptyMessageDelayed(FLUSH, 2000);
					}
					break;
			}
		};
	};

	/**
	 * 轮播图是否运行
	 */
	private boolean isRunning = false;

	@Override
	/**
	 *  当该view显示在窗体上的时，调用
	 */
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		isRunning=true;
	}

	@Override
	/**
	 *  当该view从窗体上，去掉时，调用
	 */
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		isRunning = false;
	}

	private int downX;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// 默认情况下，我们能收到一个down事件和前几个move事件，然后事件被父view中断，我们还能收到一个Cancel事件
		System.out.println("ev.getAction():"+ev.getAction());


		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = (int) ev.getX();
				downY = (int) ev.getY();
				// 大声一叫，把事件给我，谁也别中断
				requestDisallowInterceptTouchEvent(true);// 请求所有的父view别中断事件，把事件，给我
				break;

			case MotionEvent.ACTION_MOVE:
				// 只有确定是水平滑动时，才按以下逻辑处理，如果是竖直方向滑动，轮播图不请求任务事件

				int moveY = (int) ev.getY();
				int moveX = (int) ev.getX();

				int disX = Math.abs(downX - moveX);
				int disY = Math.abs(downY - moveY);

				if(disX>disY && disX>5){ // 确认是水平滑动

					// 当在第一个页面，手指向右滑。我们不需要事件
					if(getCurrentItem() == 0 && moveX>downX){
						requestDisallowInterceptTouchEvent(false);

					}else if(getCurrentItem() == imageUrlList.size()-1 && downX > moveX){
						// 当在最后一个页面，手指向左滑，我们不需要事件，
						requestDisallowInterceptTouchEvent(false);
					}else{
						// 其他情况，我们需要事件
						requestDisallowInterceptTouchEvent(true);
					}

				}else{
					// 竖直方向滑动，我们不要任何事件
					requestDisallowInterceptTouchEvent(false);
				}
				break;
			case MotionEvent.ACTION_UP:
				break;
		}

		return super.dispatchTouchEvent(ev);
	}

	private int downY;

	private long downTime;
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = (int) ev.getX();
				downY = (int) ev.getY();
				downTime = SystemClock.uptimeMillis();

				isRunning = false;
				handler.removeMessages(FLUSH); // 取消还没执行的延时消息
				break;
			case MotionEvent.ACTION_MOVE:

				break;
			case MotionEvent.ACTION_UP:
				startRoll();

				int upX = (int) ev.getX();
				int upY = (int) ev.getY();
				long upTime = SystemClock.uptimeMillis();

				if(Math.abs(downX-upX)<15 && Math.abs(upY - downY)<15 && upTime-downTime<500){
					// 认为是点击的动作,触发点击的监听
					if(onItemClickListener!=null){
						onItemClickListener.onItemClick(getCurrentItem());
					}
				}

				break;
			case MotionEvent.ACTION_CANCEL:
				// 当事件被父view中断的时候，我们没有UP事件，但会收到一个cancel事件
				startRoll();
				break;
		}
		return super.onTouchEvent(ev);
	}



	private MyAdapter adapter;

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageUrlList.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View  view = View.inflate(getContext(), R.layout.rollviewpage_imag_item, null);
			ImageView img= (ImageView) view.findViewById(R.id.image);
			container.addView(view);

			String imageUrl = imageUrlList.get(position);
			/**
			 * 三级缓存：
			 * 一： 缓存在内存中的map集合 <key,value> ,key一般用图片的url 地址，value就是图片
			 * 		* 如果所有的图片都存放在内存中，可能会造成oom 异常， out of memary 内存溢出
			 * 		* v4jar包中的 LruCache 的工具, lru算法( less recent use 最少最近的使用算法)
			 * 二: 缓存在SD卡的指定目录中。如：/mnt/sdcard/zhbjz7/image/cache/xxxxx.png
			 * 			* 本地保存图片的名称一般用图片url地址的md5值来保存
			 * 三：缓存在网络中。
			 * 使用步骤：
			 * 	一：先从内存中取图片，如果有就直接用，如果没有，就查SD卡
			 *  二：从SD卡中取图片，如果有，就图片加载至内存，然后，执行第一步,
			 *  三：如果SD卡中也没有,那么就从网络中获得，然后，保存在SD加，同时保存至内存,设置给imageView
			 */
//			Utils.butUtils(img, imageUrl,R.drawable.item_background);

			//butUtils(img, imageUrl);

			Glide.with(ctx).load(imageUrl).placeholder(R.mipmap.j).error(R.mipmap.j).into(img);

			/*Glide.with(this).load(imageUrl).override(800, 800).into(imageView);*/


			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView((View)object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}


	private OnItemClickListener onItemClickListener;


	public void setOnItemClickListener(OnItemClickListener onItemClickListener){
		this.onItemClickListener = onItemClickListener;
	}

	/**
	 * 轮播图条目点击的事件监听
	 * @author leo
	 * 点击的动作定义：手指移动，不超过20个像素，按下的时间，不超过500毫秒，认为是点击的动作
	 */
	public interface OnItemClickListener{
		/**
		 * 当点击某个条目时，回调此方法
		 * @param currPosition 点击的条目的下标
		 */
		void onItemClick(int currPosition);
	}

	/**
	 * 设置图片
	 *
	 * @param iv_img
	 * @param url
	 */

	 /*
	private void butUtils(ImageView iv_img, String url) {
		// 设置加载图片的参数
		ImageOptions options = new ImageOptions.Builder()
				// 是否忽略GIF格式的图片
				.setIgnoreGif(false)
				// 图片缩放模式
				.setImageScaleType(ScaleType.CENTER_CROP)
				// 下载中显示的图片
				.setLoadingDrawableId(R.mipmap.j)
				// 下载失败显示的图片
				.setFailureDrawableId(R.mipmap.j)
				// 得到ImageOptions对象
				.build();
		// 加载图片
		// "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg"
		x.image().bind(iv_img,url, options, new CommonCallback<Drawable>() {
			@Override
			public void onSuccess(Drawable arg0) {

				Toast.makeText(ctx, "下载成功", 0).show();
			}

			@Override
			public void onFinished() {
				Toast.makeText(ctx, "下载完成", 0).show();
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {

				Toast.makeText(ctx, "下载出错，"+arg0.getMessage(), 0).show();
				System.out.println(arg0.getMessage());
			}

			@Override
			public void onCancelled(CancelledException arg0) {
				Toast.makeText(ctx, "下载取消", 0).show();
			}
		});
	}*/




}

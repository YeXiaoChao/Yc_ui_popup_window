package com.yanis.popup_window;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		OnKeyListener {
	PopupWindow pop;
	TextView hideView;
	Button btnCancel;
	ImageView btnNight, btnWord, btnExit;
	View view;
	boolean isOut, isIn;// 是否弹窗显示

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initData();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		hideView = (TextView) findViewById(R.id.hideView);

		LayoutInflater inflater = LayoutInflater.from(this);
		// 引入窗口配置文件 - 即弹窗的界面
		view = inflater.inflate(R.layout.menu_view, null);
		btnNight = (ImageView) view.findViewById(R.id.btnNight);
		btnWord = (ImageView) view.findViewById(R.id.btnWord);
		btnExit = (ImageView) view.findViewById(R.id.btnExit);
		btnCancel = (Button) view.findViewById(R.id.btnCancel);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		btnNight.setOnClickListener(this);
		btnWord.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		view.setFocusableInTouchMode(true);
		view.setOnKeyListener(this);
		// PopupWindow实例化
		pop = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		/**
		 * PopupWindow 设置
		 */
		// pop.setFocusable(true); //设置PopupWindow可获得焦点
		// pop.setTouchable(true); //设置PopupWindow可触摸
		// pop.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
		// 设置PopupWindow显示和隐藏时的动画
		pop.setAnimationStyle(R.style.MenuAnimationFade);
		/**
		 * 改变背景可拉的弹出窗口。后台可以设置为null。 这句话必须有，否则按返回键popwindow不能消失 或者加入这句话
		 * ColorDrawable dw = new
		 * ColorDrawable(-00000);pop.setBackgroundDrawable(dw);
		 */
		pop.setBackgroundDrawable(new BitmapDrawable());

	}

	/**
	 * 按钮点击事件监听
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNight:
			changePopupWindowState();
			Toast.makeText(MainActivity.this, "你点击了夜间模式", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnWord:
			changePopupWindowState();
			Toast.makeText(MainActivity.this, "你点击了文本模式", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnExit:
			exitTheDemo();
			break;
		case R.id.btnCancel:
			changePopupWindowState();
			break;
		}
	}

	/**
	 * 退出程序
	 */
	private void exitTheDemo() {
		changePopupWindowState();
		new AlertDialog.Builder(MainActivity.this).setMessage("确定退出这个 Demo 吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("取消", null).show();
	}

	/**
	 * 改变 PopupWindow 的显示和隐藏
	 */
	private void changePopupWindowState() {
		if (pop.isShowing()) {
			// 隐藏窗口，如果设置了点击窗口外消失，则不需要此方式隐藏
			pop.dismiss();
		} else {
			// 弹出窗口显示内容视图,默认以锚定视图的左下角为起点，这里为点击按钮
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
	}

	// Called when a key was pressed down and not handled by any of the views
	// inside of the activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:// 菜单键监听
			isOut = true;
			changePopupWindowState();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// Called when a hardware key is dispatched to a view.
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			if (isOut && !isIn) {
				isOut = false;
				isIn = true;
			} else if (!isOut && isIn) {
				isIn = false;
				changePopupWindowState();
			}
			break;
		}
		return false;
	}

}

package com.yanis.popup_window;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	PopupWindow pop;
	TextView hideView;
	Button btnCancel;
	ImageView btnNight, btnWord, btnExit;
	View view;

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
		// PopupWindow实例化
		pop = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (pop.isShowing()) {
			// 隐藏窗口，如果设置了点击窗口外消失，则不需要此方式隐藏
			pop.dismiss();
		} else {
			// 弹出窗口显示内容视图,默认以锚定视图的左下角为起点，这里为点击按钮
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (pop.isShowing()) {
			// 隐藏窗口，如果设置了点击窗口外消失，则不需要此方式隐藏
			pop.dismiss();
		} else {
			// 弹出窗口显示内容视图,默认以锚定视图的左下角为起点，这里为点击按钮
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNight:
			closeThePopupWindow();
			Toast.makeText(MainActivity.this, "你点击了夜间模式", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnWord:
			closeThePopupWindow();
			Toast.makeText(MainActivity.this, "你点击了文本模式", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnExit:
			closeThePopupWindow();
			finish();
			break;
		case R.id.btnCancel:
			closeThePopupWindow();
			break;
		}
	}

	/**
	 * 关闭 PopupWindow
	 */
	private void closeThePopupWindow() {
		if (pop != null) {
			pop.dismiss();
		}
	}

}

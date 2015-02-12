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
	 * ��ʼ�����
	 */
	private void initView() {
		hideView = (TextView) findViewById(R.id.hideView);

		LayoutInflater inflater = LayoutInflater.from(this);
		// ���봰�������ļ� - �������Ľ���
		view = inflater.inflate(R.layout.menu_view, null);
		btnNight = (ImageView) view.findViewById(R.id.btnNight);
		btnWord = (ImageView) view.findViewById(R.id.btnWord);
		btnExit = (ImageView) view.findViewById(R.id.btnExit);
		btnCancel = (Button) view.findViewById(R.id.btnCancel);

	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		btnNight.setOnClickListener(this);
		btnWord.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		// PopupWindowʵ����
		pop = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (pop.isShowing()) {
			// ���ش��ڣ���������˵����������ʧ������Ҫ�˷�ʽ����
			pop.dismiss();
		} else {
			// ����������ʾ������ͼ,Ĭ����ê����ͼ�����½�Ϊ��㣬����Ϊ�����ť
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (pop.isShowing()) {
			// ���ش��ڣ���������˵����������ʧ������Ҫ�˷�ʽ����
			pop.dismiss();
		} else {
			// ����������ʾ������ͼ,Ĭ����ê����ͼ�����½�Ϊ��㣬����Ϊ�����ť
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNight:
			closeThePopupWindow();
			Toast.makeText(MainActivity.this, "������ҹ��ģʽ", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnWord:
			closeThePopupWindow();
			Toast.makeText(MainActivity.this, "�������ı�ģʽ", Toast.LENGTH_SHORT)
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
	 * �ر� PopupWindow
	 */
	private void closeThePopupWindow() {
		if (pop != null) {
			pop.dismiss();
		}
	}

}

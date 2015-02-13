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
	boolean isOut, isIn;// �Ƿ񵯴���ʾ

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
		view.setFocusableInTouchMode(true);
		view.setOnKeyListener(this);
		// PopupWindowʵ����
		pop = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		/**
		 * PopupWindow ����
		 */
		// pop.setFocusable(true); //����PopupWindow�ɻ�ý���
		// pop.setTouchable(true); //����PopupWindow�ɴ���
		// pop.setOutsideTouchable(true); // ���÷�PopupWindow����ɴ���
		// ����PopupWindow��ʾ������ʱ�Ķ���
		pop.setAnimationStyle(R.style.MenuAnimationFade);
		/**
		 * �ı䱳�������ĵ������ڡ���̨��������Ϊnull�� ��仰�����У����򰴷��ؼ�popwindow������ʧ ���߼�����仰
		 * ColorDrawable dw = new
		 * ColorDrawable(-00000);pop.setBackgroundDrawable(dw);
		 */
		pop.setBackgroundDrawable(new BitmapDrawable());

	}

	/**
	 * ��ť����¼�����
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNight:
			changePopupWindowState();
			Toast.makeText(MainActivity.this, "������ҹ��ģʽ", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnWord:
			changePopupWindowState();
			Toast.makeText(MainActivity.this, "�������ı�ģʽ", Toast.LENGTH_SHORT)
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
	 * �˳�����
	 */
	private void exitTheDemo() {
		changePopupWindowState();
		new AlertDialog.Builder(MainActivity.this).setMessage("ȷ���˳���� Demo ��")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("ȡ��", null).show();
	}

	/**
	 * �ı� PopupWindow ����ʾ������
	 */
	private void changePopupWindowState() {
		if (pop.isShowing()) {
			// ���ش��ڣ���������˵����������ʧ������Ҫ�˷�ʽ����
			pop.dismiss();
		} else {
			// ����������ʾ������ͼ,Ĭ����ê����ͼ�����½�Ϊ��㣬����Ϊ�����ť
			pop.showAtLocation(hideView, Gravity.BOTTOM, 0, 0);
		}
	}

	// Called when a key was pressed down and not handled by any of the views
	// inside of the activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:// �˵�������
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

package com.lhq.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Wesley on 2015/11/8.
 */
public class WeixinActivity extends FragmentActivity implements OnClickListener {

    private LinearLayout tabWeiXin;
    private LinearLayout tabFriend;
    private LinearLayout tabAddress;
    private LinearLayout tabSetting;

    private ImageButton btnWeiXin;
    private ImageButton btnFriend;
    private ImageButton btnAddress;
    private ImageButton btnSetting;

    private Fragment fragmentWeiXin;
    private Fragment fragmentFriend;
    private Fragment fragmentAddress;
    private Fragment fragmentSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");

        setContentView(R.layout.main);
        initView();
        initEvent();
        setSelect(0);
    }

    @Override
    public void onClick(View v) {
        resetImage();
        switch(v.getId()) {
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_friend:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_setting:
                setSelect(3);
                break;
            default:
                break;
        }
    }

    /**
     * init linearlayout event
     */
    private void initEvent() {
        tabWeiXin.setOnClickListener(this);
        tabFriend.setOnClickListener(this);
        tabAddress.setOnClickListener(this);
        tabSetting.setOnClickListener(this);
    }

    /**
     * init LinearLayout and ImageButton
     */
    private void initView() {
        tabWeiXin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        tabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        tabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        tabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        btnWeiXin = (ImageButton) findViewById(R.id.weixin);
        btnFriend = (ImageButton) findViewById(R.id.friend);
        btnAddress = (ImageButton) findViewById(R.id.address);
        btnSetting = (ImageButton) findViewById(R.id.setting);
    }

    /**
     * reset imageButton
     */
    private void resetImage() {
        btnWeiXin.setImageResource(R.mipmap.tab_weixin_normal);
        btnFriend.setImageResource(R.mipmap.tab_find_frd_normal);
        btnAddress.setImageResource(R.mipmap.tab_address_normal);
        btnSetting.setImageResource(R.mipmap.tab_settings_normal);
    }

    /**
     * select tab
     */
    private void setSelect(int i) {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction transaction = fragment.beginTransaction();
        hideFragment(transaction);
        switch(i) {
            case 0:
                if (fragmentWeiXin == null) {
                    fragmentWeiXin = new WeixinFragment();
                    transaction.add(R.id.tab_content, fragmentWeiXin);
                } else {
                    transaction.show(fragmentWeiXin);
                }
                btnWeiXin.setImageResource(R.mipmap.tab_weixin_pressed);
                break;
            case 1:
                if (fragmentFriend == null) {
                    fragmentFriend = new FriendFragment();
                    transaction.add(R.id.tab_content, fragmentFriend);
                } else {
                    transaction.show(fragmentFriend);
                }
                btnFriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                break;
            case 2:
                if (fragmentAddress == null) {
                    fragmentAddress = new AddressFragment();
                    transaction.add(R.id.tab_content, fragmentAddress);
                } else {
                    transaction.show(fragmentAddress);
                }
                btnAddress.setImageResource(R.mipmap.tab_address_pressed);
                break;
            case 3:
                if (fragmentSetting == null) {
                    fragmentSetting = new SettingFragment();
                    transaction.add(R.id.tab_content, fragmentSetting);
                } else {
                    transaction.show(fragmentSetting);
                }
                btnSetting.setImageResource(R.mipmap.tab_settings_pressed);
                break;
            default:
                break;
        }

        transaction.commit();
    }

    /**
     * hide fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentWeiXin != null) {
            transaction.hide(fragmentWeiXin);
        }
        if (fragmentFriend != null) {
            transaction.hide(fragmentFriend);
        }
        if (fragmentAddress != null) {
            transaction.hide(fragmentAddress);
        }
        if (fragmentSetting != null) {
            transaction.hide(fragmentSetting);
        }
    }
}

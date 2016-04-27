package com.kevin.drift.Activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kevin.drift.Fragment.FriendsFragment;
import com.kevin.drift.Fragment.MessageFragment;
import com.kevin.drift.Fragment.ProFileFragment;
import com.kevin.drift.Fragment.WorldFragment;
import com.kevin.drift.R;
import com.zhy.android.percent.support.PercentLinearLayout;

/**
 * Created by Benson_Tom on 2016/4/27.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG= "HomeActivity";
    private PercentLinearLayout mTabWorld;
    private PercentLinearLayout mTabFriends;
    private PercentLinearLayout mTabMessage;
    private PercentLinearLayout mTabProfile;

    private ImageButton mImgBtWord;
    private ImageButton mImgBtFriends;
    private ImageButton mImgBtMessage;
    private ImageButton mImgBtProfile;

    private TextView mTabTextWorld;
    private TextView mTabTextFriends;
    private TextView mTabTextMessage;
    private TextView mTabTextProfile;

    private Fragment mWorldFragment;
    private Fragment mFriendsFragment;
    private Fragment mMessageFragment;
    private Fragment mProfileFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Log.i(TAG,"comming");
        initWidget();
        initEvent();
        setSelect(0);
    }


    private void initWidget() {
        /**
         * 底部布局Tab的查找
         */
        mTabWorld = (PercentLinearLayout) this.findViewById(R.id.id_tabBarWorld);
        mTabFriends = (PercentLinearLayout) this.findViewById(R.id.id_tabBarFriends);
        mTabMessage = (PercentLinearLayout) this.findViewById(R.id.id_tabBarMessage);
        mTabProfile = (PercentLinearLayout) this.findViewById(R.id.id_tabBarProfile);

        mImgBtWord = (ImageButton) this.findViewById(R.id.id_tabBarWorld_img);
        mImgBtFriends = (ImageButton) this.findViewById(R.id.id_tabBarFriends_img);
        mImgBtMessage = (ImageButton) this.findViewById(R.id.id_tabBarMessage_img);
        mImgBtProfile = (ImageButton) this.findViewById(R.id.id_tabBarProfile_img);

        mTabTextWorld = (TextView) this.findViewById(R.id.id_tabBarWorld_tv);
        mTabTextFriends = (TextView) this.findViewById(R.id.id_tabBarFriends_tv);
        mTabTextMessage = (TextView) this.findViewById(R.id.id_tabBarMessage_tv);
        mTabTextProfile = (TextView) this.findViewById(R.id.id_tabBarProfile_tv);

    }

    private void initEvent() {
        mTabWorld.setOnClickListener(this);
        mTabFriends.setOnClickListener(this);
        mTabMessage.setOnClickListener(this);
        mTabProfile.setOnClickListener(this);
    }

    /**
     * 选择当前界面
     * @param i
     */
    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        /**
         * 在现实新的界面的时候，先隐藏之前的界面
         * 通过Switch判断
         */
        hideFragment(transaction);

        switch (i) {
            case 0:
                /**
                 * 先判断当前布局是否存在，再进行界面绘制，防止内存浪费
                 * 1.当前视图不存在，就创建
                 * 2.视图存在，则直接显示
                 * 其他case 也一样
                 */
                if (mWorldFragment == null) {
                    mWorldFragment = new WorldFragment();


                    transaction.add(R.id.id_frameLayout, mWorldFragment);
                }else {
                    transaction.show(mWorldFragment);
                }
                //选择了该界面，则对应的图标换成有颜色的
                mImgBtWord.setImageResource(R.mipmap.tabbar_world_selected);
                mTabTextWorld.setTextColor(Color.parseColor("#FF8E29"));
                break;
            case 1:
                if (mFriendsFragment == null) {
                    mFriendsFragment = new FriendsFragment();
                    transaction.add(R.id.id_frameLayout, mFriendsFragment);

                }else {
                    transaction.show(mFriendsFragment);
                }

                mImgBtFriends.setImageResource(R.mipmap.tabbar_friends_selected);
                mTabTextFriends.setTextColor(Color.parseColor("#FF8E29"));
                break;

            case 2:
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    transaction.add(R.id.id_frameLayout, mMessageFragment);
                }else {
                    transaction.show(mMessageFragment);
                }
                mImgBtMessage.setImageResource(R.mipmap.tabbar_message_center_highlighted);
                mTabTextMessage.setTextColor(Color.parseColor("#FF8E29"));
                break;

            case 3:
                if (mProfileFragment == null) {
                    mProfileFragment = new ProFileFragment();
                    transaction.add(R.id.id_frameLayout, mProfileFragment);

                }else {
                    transaction.show(mProfileFragment);
                }
                mImgBtProfile.setImageResource(R.mipmap.tabbar_profile_highlighted);
                mTabTextProfile.setTextColor(Color.parseColor("#FF8E29"));
                break;


            default:
                break;
        }
        //提交事务
        transaction.commit();
    }

    /**
     * 拥有隐藏Fragment，防止界面重叠
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction)
    {
        /**
         * 判断当前显示界面是否为空，不为空就需要隐藏，空表示该界面并未显示，不需隐藏操作
         */
        if (mWorldFragment != null)
        {
            transaction.hide(mWorldFragment);
        }
        if (mFriendsFragment != null)
        {
            transaction.hide(mFriendsFragment);
        }
        if (mMessageFragment != null)
        {
            transaction.hide(mMessageFragment);
        }
        if (mProfileFragment != null)
        {
            transaction.hide(mProfileFragment);
        }
    }

    /**
     * 每次选定当前界面的时候，需要调用该方法，用于界面底部Tab各个tab的图标初始化，恢复成无颜色状态
     */
    private void resetImgs(){
        mImgBtWord.setImageResource(R.mipmap.tabbar_worlds);
        mImgBtFriends.setImageResource(R.mipmap.tabbar_friends);
        mImgBtMessage.setImageResource(R.mipmap.tabbar_message_center);
        mImgBtProfile.setImageResource(R.mipmap.tabbar_profile);

        mTabTextWorld.setTextColor(Color.BLACK);
        mTabTextFriends.setTextColor(Color.BLACK);
        mTabTextMessage.setTextColor(Color.BLACK);
        mTabTextProfile.setTextColor(Color.BLACK);

    }





    /**
     * 监听事件的处理，根据点击的按钮，来选定要显示的界面
     */
    @Override
    public void onClick(View v) {
        resetImgs();//初始化各个图标的颜色，先将各个图标设置为无颜色图标
        switch (v.getId()) {
            case R.id.id_tabBarWorld:
                setSelect(0);
                Log.i(TAG,"点击了");
                break;
            case R.id.id_tabBarFriends:
                setSelect(1);
                Log.i(TAG,"点击了");
                break;
            case R.id.id_tabBarMessage:
                Log.i(TAG,"点击了");
                setSelect(2);
                break;
            case R.id.id_tabBarProfile:
                Log.i(TAG,"点击了");
                setSelect(3);
                break;
            default:
                break;
        }

    }
}
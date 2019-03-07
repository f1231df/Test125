package com.example.test125.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.test125.R;
import com.example.test125.base.BaseFragment;
import com.example.test125.community.CommunityFragment;
import com.example.test125.home.fragment.HomeFragment;
import com.example.test125.shoppingcart.ShoppingCartFragment;
import com.example.test125.type.TypeFragment;
import com.example.test125.user.UserFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {


   private FrameLayout frameLayout;

    private RadioGroup rgMain;

    /* private FrameLayout frameLayout;
             private RadioGroup rgMain;*/
    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        frameLayout=findViewById(R.id.frameLayout);
        rgMain=findViewById(R.id.rg_main);
        //主界面的初始化
        initFragment();
        //主界面的切换监听
        initListener();
    }

    /**
     * 将页面添加到ArrayList数组中
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    /**
     * 根据按钮选择对应主界面
     */
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home: //首页
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;  //分类
                        break;
                    case R.id.rb_community:
                        position = 2;   //社区
                        break;
                    case R.id.rb_cart:
                        position = 3;   //购物车
                        break;
                    case R.id.rb_user:  //用户中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据不同位置取不同的fragment
                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    /**
     * 得到所选界面的位置
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 页面切换
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}


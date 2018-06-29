package cn.hnust.book.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.view.fragment.MainFragmentFactory;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;

    //保存每个Fragment的Tag，刷新页面的依据
    private SparseArray<String> tags = new SparseArray<>();

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commit();
        String tag = fragment.getTag();
        //保存每个Fragment的Tag
        tags.put(position, tag);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
        Fragment fragment = getFragmentByPosition(position);
        mFragmentManager.beginTransaction().hide(fragment).commit();
        super.destroyItem(container, position, object);
    }

    //拿到指定位置的Fragment
    public Fragment getFragmentByPosition(int position) {
        return mFragmentManager.findFragmentByTag(tags.get(position));
    }

    public List<Fragment> getFragments() {
        return mFragmentManager.getFragments();
    }

    //刷新指定位置的Fragment
    public void notifyFragmentByPosition(int position) {
        tags.removeAt(position);
        notifyDataSetChanged();
    }

    //刷新指定位置的Fragment
    public void notifyFragments() {
        tags.clear();
        notifyDataSetChanged();
    }

    //除了给定位置，其他位置的Fragment进行刷新
    public void notifyChangeWithoutPosition(int position) {
        String valueP = tags.valueAt(position);
        tags.clear();
        tags.put(position, valueP);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment) object;
        //如果Item对应的Tag存在，则不进行刷新
        if (tags.indexOfValue(fragment.getTag()) > -1) {
            return super.getItemPosition(object);
        }
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragmentFactory.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }


}

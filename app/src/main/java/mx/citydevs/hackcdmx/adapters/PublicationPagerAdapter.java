package mx.citydevs.hackcdmx.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PublicationPagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> listFragments;
	
	public PublicationPagerAdapter(FragmentManager fm) {
		super(fm);
		this.listFragments = new ArrayList<Fragment>();
	}

	/**
	 * Add a new fragment in the list.
	 * 
	 * @param fragment a new fragment
	 */
	public void addFragment(Fragment fragment) {
		this.listFragments.add(fragment);
	}

	@Override
	public Fragment getItem(int position) {
		return this.listFragments.get(position);
	}

	@Override
	public int getCount() {
		return this.listFragments.size();
	}
}

package com.test.test.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.test.test.R;


public class MainFragment extends Fragment {

    private  TabLayout tabLayout;
    private  ViewPager viewPager;
    private  int int_items = 4 ;
    private EditText edtText;
    private ImageButton attachBtn,closeBtn,micBtn,upBtn;
    View textLayout;
    View viewPagerLayout;


    private int[] tabIcons = {
            R.drawable.ic_gallery_n,
            R.drawable.ic_docs_n,
            R.drawable.ic_contact_n,
            R.drawable.ic_camera_n
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View x =  inflater.inflate(R.layout.main_fragment,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        edtText= (EditText) x.findViewById(R.id.edt_text);
        micBtn= (ImageButton) x.findViewById(R.id.mic_btn);
        upBtn= (ImageButton) x.findViewById(R.id.up_btn);
        attachBtn= (ImageButton) x.findViewById(R.id.attach_button);
        closeBtn= (ImageButton) x.findViewById(R.id.close_button);
        textLayout= (View) x.findViewById(R.id.text_layout);
        viewPagerLayout= (View) x.findViewById(R.id.viewpager_layout);


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "proxima_nova_reg.ttf");
        edtText.setTypeface(custom_font);



        setupTabIcons();

        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getActivity(), R.color.highlight_blue);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getActivity(), R.color.battleship);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.highlight_blue), PorterDuff.Mode.SRC_IN);

        attachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                textLayout.setVisibility(View.GONE);
                viewPagerLayout.setVisibility(View.VISIBLE);



            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showKeyboard();
                textLayout.setVisibility(View.VISIBLE);
                viewPagerLayout.setVisibility(View.GONE);
            }
        });


        edtText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    micBtn.setVisibility(View.GONE);
                    upBtn.setVisibility(View.VISIBLE);
                }
                else{
                    micBtn.setVisibility(View.VISIBLE);
                    upBtn.setVisibility(View.GONE);
                }

            }
        });



        hideKeyboard();

        return x;

    }

    public  void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public  void showKeyboard() {

        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }




    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return new PrimaryFragment();
              case 1 : return new SecondFragment();
              case 2 : return new ThirdFragment();
              case 3 : return new FourthFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position) {

                return null;
        }



    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}

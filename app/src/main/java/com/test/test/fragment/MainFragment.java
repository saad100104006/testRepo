package com.test.test.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.test.test.R;


public class MainFragment extends Fragment {

    private  TabLayout tabLayout;
    private  ViewPager viewPager;
    private  int int_items = 4 ;
    private EditText edtText;
    private ImageButton attachBtn,closeBtn;

    private FloatingActionButton micBtn,upBtn;
    RelativeLayout cut;
    View textLayout;
    View viewPagerLayout;

    int flg=0;
    int flagKeyboard=0;
    int attachFlg=0;


    private int[] tabIcons = {
            R.drawable.ic_gallery_n,
            R.drawable.ic_docs_n,
            R.drawable.ic_contact_n,
            R.drawable.ic_camera_n
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View x =  inflater.inflate(R.layout.main_fragment,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        edtText= (EditText) x.findViewById(R.id.edt_text);
        micBtn= (FloatingActionButton) x.findViewById(R.id.mic_btn);
        upBtn= (FloatingActionButton) x.findViewById(R.id.up_btn);
        attachBtn= (ImageButton) x.findViewById(R.id.attach_button);
        closeBtn= (ImageButton) x.findViewById(R.id.close_button);
        textLayout= (View) x.findViewById(R.id.text_layout);
        viewPagerLayout= (View) x.findViewById(R.id.viewpager_layout);
        cut=(RelativeLayout)(View) x.findViewById(R.id.cut);


        micBtn.setImageResource(R.drawable.ic_mic);
        micBtn.setCompatElevation(8f);


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "proxima_nova_reg.ttf");
        edtText.setTypeface(custom_font);



        setupTabIcons();


        x.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                x.getWindowVisibleDisplayFrame(r);

                int heightDiff = x.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 500) { // if more than 100 pixels, its probably a keyboard...
                    flagKeyboard=1;
                }
                else{
                if(attachFlg!=1)
                    flagKeyboard=0;
                }
            }
        });

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
                attachFlg=1;

                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm.isAcceptingText()) {

                   // flagKeyboard=1;
                 //   writeToLog("Software Keyboard was shown");
                } else {

                    //flagKeyboard=0;
                   // writeToLog("Software Keyboard was not shown");
                }


                hideKeyboard();
                textLayout.setVisibility(View.GONE);
                viewPagerLayout.setVisibility(View.VISIBLE);


                Animation aniFade = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                viewPagerLayout.startAnimation(aniFade);



            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                attachFlg=0;

                if(edtText.getText().toString().trim().length() > 0 || flagKeyboard==1) {
                    showKeyboard();
                }
                textLayout.setVisibility(View.VISIBLE);
                viewPagerLayout.setVisibility(View.GONE);


                Animation aniFade = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                textLayout.startAnimation(aniFade);



            }
        });


        edtText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length() != 0)
                {
                    flg++;


                    if(flg==1) {

                        cut.setVisibility(View.GONE);


                        //  slideDown(micBtn);

                        TranslateAnimation animate = new TranslateAnimation(
                                0,                 // fromXDelta
                                0,                 // toXDelta
                                0,                 // fromYDelta
                                65); // toYDelta
                        animate.setDuration(150);
                        animate.setFillAfter(true);
                        micBtn.startAnimation(animate);

                        animate.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                micBtn.setSize(FloatingActionButton.SIZE_MINI);
                                micBtn.setImageResource(R.drawable.ic_send);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }



                }
                else{
                    flg=0;

                    // slideUp(micBtn);


                  //  micBtn.setVisibility(View.VISIBLE);
                    TranslateAnimation animate = new TranslateAnimation(
                            0,                 // fromXDelta
                            0,                 // toXDelta
                            65,  // fromYDelta
                            0);                // toYDelta
                    animate.setDuration(150);
                    animate.setFillAfter(true);
                    micBtn.startAnimation(animate);


                    animate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            micBtn.setSize(FloatingActionButton.SIZE_AUTO);
                            micBtn.setImageResource(R.drawable.ic_mic);
                            micBtn.setCompatElevation(8f);
                            cut.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    //micBtn.show();
                    //micBtn.setVisibility(View.VISIBLE);
                    //upBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });



        hideKeyboard();

        return x;

    }


    // slide the view from below itself to the current position
    public void slideUp(View view){

    }

    // slide the view from its current position to below itself
    public void slideDown(View view){

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

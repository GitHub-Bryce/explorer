/**
	 * 初始化菜单
	 * */
private void initAppMenu(){
    	appMenu = (LinearLayout)findViewById(R.id.appmenu);
    	LinearLayout row = (LinearLayout) appMenu.findViewById(R.id.approw1);
    	LayoutInflater infl = getLayoutInflater();
    	View.OnClickListener ocl = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				appMenuClick(v.getId());
				hideAppMenu();
			}
		};
		
		int[] drRes = {R.drawable.newfolder, R.drawable.newfile, R.drawable.paste,
				R.drawable.search, R.drawable.dialog, R.drawable.apkmanager,
				R.drawable.setting, R.drawable.multicon, R.drawable.filelib,
				R.drawable.close};
		String[] names = getResources().getStringArray(R.array.appnames);
		for (int i = 0; i < 10; i++){
			if (i == 5) {
				row = (LinearLayout) appMenu.findViewById(R.id.approw2);
			}
			RelativeLayout rl = (RelativeLayout) infl.inflate(R.layout.appmenuitem,
					null);
			ImageButton iv = (ImageButton) rl.findViewById(R.id.menuicon);
			//iv.setImageResource(drRes[i]);
			iv.setBackgroundResource(drRes[i]);
			TextView tv = (TextView) rl.findViewById(R.id.menuname);
			tv.setText(names[i]);
			iv.setId(i);
			iv.setOnClickListener(ocl);
			row.addView(rl);
			if ( i == MENU_SET_VIEW_STYLE){
				viewStyleButton = iv;
				viewStyleTextView = tv;
			}
		}
    	
    }

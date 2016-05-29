/**
     * 显示菜单栏, 重新实现的Option menu.
     * */
    private void showAppMenu() {
    	if (menuShowAnimation == null) {
    		menuShowAnimation = AnimationUtils
    				.loadAnimation(this, R.anim.menuhide);
    	}
    	appMenu.startAnimation(menuShowAnimation);
    	appMenu.setVisibility(View.VISIBLE);
    }


/**
     * 隐藏菜单栏, 重新实现的Option menu.
     * */
    private void hideAppMenu() {
    	appMenu.setVisibility(View.INVISIBLE);
    	if (menuHideAnimation == null)
    		menuHideAnimation = AnimationUtils
    				.loadAnimation(this, R.anim.menushow);
    	appMenu.startAnimation(menuHideAnimation);
    }

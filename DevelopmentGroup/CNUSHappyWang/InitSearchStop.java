    private void initSearchStop() {
    	searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (searching) {
					searching = false;
					if (searchProcess != null) {
						searchProcess.destroy();
						searchProcess = null;
					}
				} else {
					currentData.searchingTag = false;
					searchLayout.setVisibility(View.INVISIBLE);
					refreshPath(currentPath(), true);
				}
			}
		});
    }
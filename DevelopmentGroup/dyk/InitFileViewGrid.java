/*
 *显示文件
*/
private void initFileViewGrid(){
    	fileViewGrid.setFastScrollEnabled(true);
    	fileViewGrid.setGravity(Gravity.FILL);
    	fileViewGrid.setAdapter(fileAdapterGrid);
    	fileAdapterGrid.setListView(fileViewGrid);
    	fileViewGrid.setOnItemClickListener(fileViewClickListener);
    	fileViewGrid.setOnItemLongClickListener(fileViewLongClickListener);
    	fileViewGrid.setStartDragListener(stargDragListener);
    	fileViewGrid.setDropListener(dropListener);
    	//fileViewGrid.setParentView(findViewById(R.id.ddv));
    	fileViewGrid.setDropOutListener(new DropOutListener() {
			
			@Override
			public void dropOut(int from, int x, int y) {
				// TODO Auto-generated method stub
				//Log.d(tag, "fileView Bottom : " + fileView.getBottom());
				if (y > 0)
					dropListener.drop(from, -1);
			}
		});
    }

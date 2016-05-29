/**
  * 设置文件显示风格
  * */
private void setFileViewStyle(int style){
    	preViewStyle = style;
        if (preViewStyle == FileListAdapter.STYLE_LIST){
            fileAdapter = fileAdapterList;
            fileViewList.setVisibility(View.VISIBLE);
            fileViewGrid.setVisibility(View.GONE);
        	fileView = fileViewList;
			viewStyleTextView.setText(getString(R.string.icon));
			viewStyleButton.setBackgroundResource(R.drawable.multicon);
        } else {
        	fileAdapter = fileAdapterGrid;
            fileViewGrid.setVisibility(View.VISIBLE);
            fileViewList.setVisibility(View.GONE);
        	fileView = fileViewGrid;
			viewStyleTextView.setText(getString(R.string.list));
			viewStyleButton.setBackgroundResource(R.drawable.multlist);
        }
        fileAdapter.setData(currentData);
        fileAdapter.notifyDataSetChanged();
    }

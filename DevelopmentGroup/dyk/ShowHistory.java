/**
     * 显示浏览历史
     * */
private void showHistory(){

		int l = historyString.size();
		String[] str = historyString.toArray(new String[l]);
		CharSequence[] ch = new CharSequence[l];
		for (int i = 0; i < l; i++){
			if (str[i].length() == 1) {
				ch[i] = "/";
			} else
				ch[i] = str[i].substring(str[i].lastIndexOf("/") 
						+ 1, str[i].length());
		}
		
		new AlertDialog.Builder(FileManager.this)
			.setTitle(getString(R.string.history))
			.setItems(ch, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					// 同时更新路径栏
					String s = (String) historyString.get(which);
					if (currentPath().equals(s))
						return;
					int p = pathAdapter.getAbsolutePath().indexOf(s);
					refreshPath(s, p != 0);	
					FileManager.dbg("--" + s);
					p = s.split("/").length;
					if (p != 0) {
						p--;
					}
					pathScroll.setSelection(p);
				}

			}).setNegativeButton(cancel, new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
				
			}).create().show();
    }

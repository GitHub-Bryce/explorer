/**
     * 菜单面板
     * */
	private void appMenuClick(int whitch) {
		switch (whitch){
		case MENU_CREATE_DIRECTORY:
			listListener.onClick(null, MENU_ITEM_CREATE_DIRECTORY);
			break;
		case MENU_CREATE_FILE:
			listListener.onClick(null, MENU_ITEM_CREATE_FILE);
			break;
		case MENU_PASTE:
			listListener.onClick(null, MENU_ITEM_PASTE);
			break;
		case MENU_SEARCH:
			/**
			String expr = "sdfd";
			expr = expr.replace("*", ".*");
			searchFile("an");
			/**/
			if (searching) {
				Toast.makeText(this, getString(R.string.searching), 
						Toast.LENGTH_SHORT).show();
				break;
			}
			SearchInputDialog sid = new SearchInputDialog(this);
			sid.setOnSearchListener(onSL);
			sid.show();
			break;
		case MENU_FINISH_ACTIVITY:
			if (listListener.copying())
				dealCopyingOnExit();
			else
				this.finish();
			break;
		case MENU_SHOW_COPY_DIALOG:
			listListener.showHiddenCopyDialog();
			break;
			
		case MENU_APK_MANAGER:
			Intent intent = new Intent();
			intent.putExtra(PRE_BACKUP_DIR, preBackupDir);
			/**
			intent.setClassName("com.xjf.filedialog",
					"com.xjf.filedialog.XPackageManager");
					/**/
			intent.setAction("com.xjf.apk.EDIT");
			this.startActivity(intent);
			break;
		case MENU_SETTING:
			/**/
			
			if (settingsView == null) {
				settingsHide = true;
				settingsView = new SettingsView(this);
			}
			if (settingsHide == false) {
				settingsHide = true;
				hideSettingsView();
			} else {
				settingsHide = false;
				settingsView.show(null);
			}
			/**/
			break;
		case MENU_FILE_LIB:
			fileLibDialog.doWhat = FileLibDialog.FILE_LIB_OPEN;
			fileLibDialog.show();
			break;
		case MENU_SET_VIEW_STYLE:
			if (preViewStyle == FileListAdapter.STYLE_LIST) {
				setFileViewStyle(FileListAdapter.STYLE_GRID);
			} else {
				setFileViewStyle(FileListAdapter.STYLE_LIST);
			}
			break;
		default: break;
		}
		
	}

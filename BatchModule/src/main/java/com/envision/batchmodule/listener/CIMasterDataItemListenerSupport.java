package com.envision.batchmodule.listener;

import java.util.List;

import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.lang.Nullable;

import com.envision.cimodule.database.objects.CiMasterData;

public class CIMasterDataItemListenerSupport extends ItemListenerSupport<CiMasterData, CiMasterData> {
	@Override
	public void afterRead(CiMasterData item) {
	}

	@Override
	public void beforeRead() {
	}

	@Override
	public void onReadError(Exception ex) {
	}

	@Override
	public void afterProcess(CiMasterData item, @Nullable CiMasterData result) {
	}

	@Override
	public void beforeProcess(CiMasterData item) {
	}

	@Override
	public void onProcessError(CiMasterData item, Exception e) {
	}

	@Override
	public void afterWrite(List<? extends CiMasterData> item) {
	}


	@Override
	public void beforeWrite(List<? extends CiMasterData> item) {
	}

	
	@Override
	public void onWriteError(Exception ex, List<? extends CiMasterData> item) {
	}
}

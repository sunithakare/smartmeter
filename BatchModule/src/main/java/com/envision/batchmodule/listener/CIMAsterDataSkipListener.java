package com.envision.batchmodule.listener;

import org.springframework.batch.core.SkipListener;

public class CIMAsterDataSkipListener implements SkipListener<Integer, Integer> {

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(Integer item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(Integer item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
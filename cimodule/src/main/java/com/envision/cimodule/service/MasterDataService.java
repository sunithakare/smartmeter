package com.envision.cimodule.service;

import java.util.Optional;

import com.envision.cimodule.model.MasterDataModel;
import com.envision.common.exception.DataNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envision.cimodule.database.objects.CiMasterData;
import com.envision.cimodule.repository.MasterDataRepository;

@Service
public class MasterDataService {
	
	@Autowired
	MasterDataRepository masterDataRepository;
	
	public MasterDataModel insertMasterData( MasterDataModel masterDataModel) throws DataNotFoundException {
		Optional<CiMasterData> ciMasterDataOptional = getMasterData(masterDataModel.getAcctId());
		CiMasterData ciMasterData = null;
		if(!ciMasterDataOptional.isPresent()) {
			throw new DataNotFoundException("Account id not found " + masterDataModel.getAcctId());
		}
		ciMasterData = ciMasterDataOptional.get();
		BeanUtils.copyProperties(masterDataModel, ciMasterData);
		ciMasterData =  masterDataRepository.save(ciMasterData);
		BeanUtils.copyProperties(ciMasterData, masterDataModel);

		return masterDataModel;
	}

	public MasterDataModel updateMasterData( MasterDataModel masterDataModel) throws DataNotFoundException {
		Optional<CiMasterData> ciMasterDataOptional = getMasterData(masterDataModel.getAcctId());
		CiMasterData ciMasterData = null;
		if(ciMasterDataOptional.isPresent()) {
			throw new DataNotFoundException("Account id already found " + masterDataModel.getAcctId());
		}
		ciMasterData = ciMasterDataOptional.get();
		BeanUtils.copyProperties(masterDataModel, ciMasterData);
		ciMasterData =  masterDataRepository.save(ciMasterData);
		BeanUtils.copyProperties(ciMasterData, masterDataModel);

		return masterDataModel;
	}
	
	public Optional<CiMasterData> getMasterData(Long accountId) {
		return masterDataRepository.findById(accountId);
	}

}

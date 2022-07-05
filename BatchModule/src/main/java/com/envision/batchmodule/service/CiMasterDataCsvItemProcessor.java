package com.envision.batchmodule.service;

import org.springframework.batch.item.ItemProcessor;

import com.envision.cimodule.database.objects.CiMasterData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CiMasterDataCsvItemProcessor implements ItemProcessor<CiMasterData, CiMasterData> {

   @Override
   public CiMasterData process(final CiMasterData ciMasterDataCsv) throws Exception {
//      final String firstName = ciMasterDataCsv.gets.toUpperCase();
//      final String lastName = ciMasterDataCsv.getLastName().toUpperCase();
//      final CiMasterDataCsv transformedPerson = new ciMasterDataCsv(firstName, lastName);
//Thread.sleep(1000);
      log.info("Converting (" + ciMasterDataCsv + ")" );
      return ciMasterDataCsv;
   }
}